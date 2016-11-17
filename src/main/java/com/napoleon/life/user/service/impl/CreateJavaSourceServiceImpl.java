package com.napoleon.life.user.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.napoleon.life.common.util.StringUtil;
import com.napoleon.life.exception.CommonException;
import com.napoleon.life.exception.CommonResultCode;
import com.napoleon.life.user.createcode.DaoImplTemplate;
import com.napoleon.life.user.createcode.DaoTemplate;
import com.napoleon.life.user.createcode.Entry;
import com.napoleon.life.user.createcode.JavaBeanTemplate;
import com.napoleon.life.user.createcode.MapperImplTemplate;
import com.napoleon.life.user.createcode.SQLBean;
import com.napoleon.life.user.createcode.ServiceImplTemplate;
import com.napoleon.life.user.createcode.UpdateEntry;
import com.napoleon.life.user.service.CommonCreateCodeService;
import com.napoleon.life.user.util.SQLSource;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class CreateJavaSourceServiceImpl implements CommonCreateCodeService{

    @Value("${code.base}")
    private String codeBase;
    
    // 实体的包名称【entry】
    @Value("${entry.pkg.name}")
    private String entryPkgName; 
    
    // dao层的包名称【dao】
    @Value("${dao.pkg.name}")
    private String daoPkgName;
    
    // dao层的类名称的后缀【Dao】 ==> InvestOrderDao
    @Value("${dao.suffix.name}")
    private String daoSuffixName;
    
    // dao实现层的包名称【impl】
    @Value("${dao.impl.pkg.name}")
    private String daoImplPkgName;
    
    // dao层的类名称的后缀【Impl】 ==> InvestOrderDaoImpl
    @Value("${dao.impl.suffix.name}")
    private String daoImplSuffixName;
    
    // service层的包名称【service】
    @Value("${service.pkg.name}")
    private String servicePkgName;
    
    // service层的类名称的后缀【Service】 ==> InvestOrderService
    @Value("${service.suffix.name}")
    private String serviceSuffixName;
    
    // service实现层的包名称【impl】
    @Value("${service.impl.pkg.name}")
    private String serviceImplPkgName;
    
    // service层的类名称的后缀【Impl】 ==> InvestOrderServiceImpl
    @Value("${service.impl.suffix.name}")
    private String serviceImplSuffixName;

    // 生成代码的基目录【以/结束D:/yixin/output/】
    @Value("${create.code.location}")
    private String createCodeLocation;
    
    // GenericDaoDefault的包点类名【cn.creditease.pay.trade.core.dao.GenericDaoDefault】
    @Value("${pkg.genericDaoDefault}")
    private String genericDaoDefault;
    
    
    // Mapper文件的存放目录【trade-core-sqlmap】
    @Value("${location.mapper}")
    private String mapperLocation;
    
    // Mapper文件的后缀【Mapper】 ==> InvestOrderMapper.xml
    @Value("${mapper.file.suffix}")
    private String mapperFileSuffix;
    
    @Resource(name = "sqlSource")
    private SQLSource sqlSource;
    
    @Deprecated
    private Configuration cfg = new Configuration();

    
    /**
     * 
     * @param tableName
     *            大写的table名称
     * @return
     * @throws Exception
     */
    public JavaBeanTemplate createJavaBean(String tableName) throws RuntimeException {
        Map<String, Object> root = new HashMap<String, Object>();

        // 通过tableName创建JavaBeanName
        String className = this.createJavaBeanName(tableName);

        JavaBeanTemplate template = new JavaBeanTemplate();
        template.setPackageName(this.codeBase + "." + this.entryPkgName);  //1. javaBean 所在的package
        template.setClassName(className);  // 3. JavaBeanName
        template.setJavaBeanFullName(template.getPackageName() + "." + template.getClassName()); // 该javaBean的包点类名
        
        SQLBean sqlBean = sqlSource.getSQLBean(tableName);
        template.setPrimaryKey(JdbcUtils.convertUnderscoreNameToPropertyName(sqlBean.getPkName()));  // 主键
        template.setCodeBase(this.codeBase);
        template.setTableName(tableName.toUpperCase());
        
        List<String> columns = sqlBean.getColumnNames();  // 表的字段名称
        List<Integer> types = sqlBean.getColumnTypes();  // 字段的类型
        List<String> decimalDigits = sqlBean.getDecimalDigits();  // number 字段的精度
        List<String> remarks = sqlBean.getRemarks();  // 字段的描述
        

        //4. 包含类中的字段（字段名value，类型type，描述remark）
        List<Entry> entrys = new ArrayList<Entry>();
        //2. 该javaBean所需要导入的java类
        List<String> imports = new ArrayList<String>();

        for (int i = 0; i < columns.size(); i++) {
            Class<?> type = getType(types.get(i), decimalDigits.get(i));
            String value = JdbcUtils.convertUnderscoreNameToPropertyName(columns.get(i).toLowerCase());
//            if(template.getPrimaryKey().equals(value)){
//            	// 不需要将主键字段添加进去，huohe项目不需要bean中包含主键
//            	continue;
//            }
            String remark = remarks.get(i);
            entrys.add(new Entry(type.getSimpleName(), value, type, remark));
            if(StringUtil.notEmpty(remark) && remark.contains("Y_NO") ){
            	template.setEntityNoToQuery(value);
            	template.setEntityNoToQueryUpcase(this.upperCase(value));
            	template.setEntityNoToQueryJdbc(columns.get(i));
            }
            
            if (!type.getCanonicalName().startsWith("java.lang")) {
                if (!imports.contains(type.getCanonicalName())) {
                    imports.add(type.getCanonicalName());
                }
            }
        }

        template.setImports(imports);
        template.setEntrys(entrys);

        root.put("template", template);

        String srcPath = this.createCodeLocation + this.entryPkgName + "/" + className;
        writer(root, "javaBean.ftl", srcPath + ".java");

        return template;
    }

    public DaoTemplate createDao(JavaBeanTemplate javaBeanTemplate) throws RuntimeException {
        Map<String, Object> root = new HashMap<String, Object>();

        DaoTemplate template = new DaoTemplate();
        
        //1. dao层 所在的package
        template.setPackageName(javaBeanTemplate.getCodeBase() + "." + this.daoPkgName);
        //2. javaBeanName
        template.setJavaBeanName(javaBeanTemplate.getClassName());
        template.setDaoName(template.getJavaBeanName() + this.daoSuffixName);  // Dao 的简单名称
        template.setDaoFullName(template.getPackageName() + "." + template.getDaoName()); // Dao 的包点类名
        template.setEntityNoToQuery(javaBeanTemplate.getEntityNoToQuery());
        template.setEntityNoToQueryUpcase(javaBeanTemplate.getEntityNoToQueryUpcase());
        template.setEntityNoToQueryJdbc(javaBeanTemplate.getEntityNoToQueryJdbc());
        
        List<String> imports = new ArrayList<String>();
        imports.add(javaBeanTemplate.getJavaBeanFullName());
        template.setImports(imports);

        root.put("template", template);

        String srcPath = this.createCodeLocation + this.daoPkgName + "/" + template.getDaoName();
        writer(root, "DaoBean.ftl", srcPath + ".java");

        return template;
    }

    public DaoImplTemplate createDaoImpl(JavaBeanTemplate javaBeanTemplate, DaoTemplate daoTemplate)
            throws RuntimeException {
        Map<String, Object> root = new HashMap<String, Object>();

        DaoImplTemplate template = new DaoImplTemplate();
        template.setPackageName(daoTemplate.getPackageName() + "." + this.daoImplPkgName);
        template.setJavaBeanName(javaBeanTemplate.getClassName());
        template.setDaoBeanName(daoTemplate.getDaoName());
        
        template.setDaoImplBeanName(daoTemplate.getDaoName() + this.daoImplSuffixName);// DaoImpl 的简单名称
        template.setDaoImplFullBeanName(template.getPackageName() + "." + template.getDaoImplBeanName());  // DaoImpl 的包点类名
        template.setEntityNoToQuery(daoTemplate.getEntityNoToQuery());
        template.setEntityNoToQueryUpcase(daoTemplate.getEntityNoToQueryUpcase());
        template.setEntityNoToQueryJdbc(daoTemplate.getEntityNoToQueryJdbc());
        
        List<String> imports = new ArrayList<String>();
        imports.add(Repository.class.getCanonicalName());
        imports.add(Map.class.getCanonicalName());
        imports.add(HashMap.class.getCanonicalName());
        imports.add(this.genericDaoDefault);  // GenericDaoDefault 包点类名
        imports.add(javaBeanTemplate.getJavaBeanFullName());
        imports.add(daoTemplate.getDaoFullName());
        template.setImports(imports);

        root.put("template", template);

        String srcPath = this.createCodeLocation + this.daoPkgName + "/" + this.daoImplPkgName + "/";
        srcPath = srcPath + template.getDaoImplBeanName();
        writer(root, "DaoImplBean.ftl", srcPath + ".java");

        return template;
    }
    
    public ServiceImplTemplate createServiceImpl(JavaBeanTemplate javaBeanTemplate, DaoTemplate daoTemplate)
            throws RuntimeException {
        Map<String, Object> root = new HashMap<String, Object>();

        ServiceImplTemplate template = new ServiceImplTemplate();
        template.setServiceName(javaBeanTemplate.getClassName() + this.serviceSuffixName);
        template.setServicePackageName(javaBeanTemplate.getCodeBase() + "." + this.servicePkgName);
        template.setServiceFullName(template.getServicePackageName() + "." + template.getServiceName());
        
        template.setServiceImplName(template.getServiceName() + this.serviceImplSuffixName);
        template.setServiceImplPackageName(template.getServicePackageName() + "." + this.serviceImplPkgName);
        template.setServiceImplFullName(template.getServiceImplPackageName() + "." + template.getServiceImplName());
        
        template.setJavaBeanName(javaBeanTemplate.getClassName());
        template.setDaoBeanName(daoTemplate.getDaoName());
        template.setDaoBeanNameLowerCase(this.lowerCase(daoTemplate.getDaoName()));
        
        template.setEntityNoToQuery(daoTemplate.getEntityNoToQuery());
        template.setEntityNoToQueryUpcase(daoTemplate.getEntityNoToQueryUpcase());
        template.setEntityNoToQueryJdbc(daoTemplate.getEntityNoToQueryJdbc());
        
        List<String> imports = new ArrayList<String>();
        imports.add(javaBeanTemplate.getJavaBeanFullName());
        template.setImports(imports);

        root.put("template", template);
        
        String srcPath = this.createCodeLocation + this.servicePkgName + "/";
        writer(root, "serviceBean.ftl", srcPath + template.getServiceName() + ".java");

        imports.add(Service.class.getCanonicalName());
        imports.add(CommonResultCode.class.getCanonicalName());
        imports.add(StringUtil.class.getCanonicalName());
        imports.add(Autowired.class.getCanonicalName());
        imports.add(Transactional.class.getCanonicalName());
        imports.add(CommonException.class.getCanonicalName());
        imports.add(daoTemplate.getDaoFullName());
        imports.add(template.getServiceFullName());
        
        
        srcPath = this.createCodeLocation + this.servicePkgName + "/" + this.serviceImplPkgName + "/";
        writer(root, "serviceImplBean.ftl", srcPath + template.getServiceImplName() + ".java");

        return template;
    }

    public MapperImplTemplate createMapper(JavaBeanTemplate javaBeanTemplate, DaoTemplate daoTemplate,
            DaoImplTemplate daoImplTemplate) throws RuntimeException {
        Map<String, Object> root = new HashMap<String, Object>();

        MapperImplTemplate template = new MapperImplTemplate();
        template.setNameSpace(javaBeanTemplate.getClassName());
        template.setJavaBeanFullName(javaBeanTemplate.getJavaBeanFullName());
        SQLBean sqlBean = this.sqlSource.getSQLBean(javaBeanTemplate.getTableName());
        template.setInsert(sqlBean.getInertSql());
        template.setUpdate(sqlBean.getUpdateSql());
        template.setDelete(sqlBean.getDeleteSql());
        template.setFindById(sqlBean.getFindByIdSql());
        template.setGetAll(sqlBean.getFindAllSql());
        template.setBaseColumnList(sqlBean.getBaseColumnList());
        template.setEntityNoToQueryUpcase(javaBeanTemplate.getEntityNoToQueryUpcase());
        template.setGetByEntityNo(sqlBean.getFindAllSql() + " WHERE " + javaBeanTemplate.getEntityNoToQueryJdbc() + " = #{" + javaBeanTemplate.getEntityNoToQuery() + ", jdbcType=VARCHAR}");

        template.setPkJavaName(sqlBean.getPkBeanName());
        template.setPkName(sqlBean.getPkName());
        template.setPkNameUpdate("#{" + sqlBean.getPkBeanName() + ", jdbcType=" + sqlBean.getPkJdbcType() + "}");
        template.setTableName(javaBeanTemplate.getTableName());
        template.setPkJdbcType(sqlBean.getPkJdbcType());

        List<UpdateEntry> entrys = new ArrayList<UpdateEntry>();

        if (sqlBean.getColumnNames() != null && sqlBean.getColumnNames().size() > 0) {
            for (int i = 0; i < sqlBean.getColumnNames().size(); i++) {
                String column = sqlBean.getColumnNames().get(i);
                String javaColumn = sqlBean.getJavaBeanColumns().get(i);
                String jdbcType = sqlBean.getJdbcTypes().get(i);
                if (!sqlBean.getPkName().equals(column)) {
                    UpdateEntry entry = new UpdateEntry(column, javaColumn, "#{" + javaColumn + ", jdbcType="
                            + jdbcType + "}", jdbcType);
                    entrys.add(entry);
                }
            }
        }

        template.setUpdateEntrys(entrys);
        root.put("template", template);

        String srcPath = this.createCodeLocation + this.mapperLocation + "/" + javaBeanTemplate.getClassName() + this.mapperFileSuffix;
        writer(root, "mapperBean.ftl", srcPath + ".xml");

        return template;
    }

    public Class<?> getType(int sqlType, String decimalDigit) {
        if (sqlType == Types.VARCHAR || sqlType == Types.LONGVARCHAR || sqlType == Types.CLOB) {
            return String.class;
        } else if (sqlType == Types.DECIMAL || sqlType == Types.NUMERIC) {
            int digit = 0;
            try {
                digit = Integer.parseInt(decimalDigit);
            } catch (Exception e) {
            }

            if (digit > 0) {
                return BigDecimal.class;
            } else if (digit == 0) {
                return Integer.class;
            }
        } else if (sqlType == Types.DATE) {
            return Date.class;
        } else if (sqlType == Types.TIME) {
            return Time.class;
        } else if (sqlType == Types.TIMESTAMP) {
            return Date.class;
        } else if (sqlType == Types.BIGINT) {
            return Long.class;
        } else if (sqlType == Types.TINYINT || sqlType == Types.INTEGER) {
            return Integer.class;
        }

        return String.class;
    }

    public void writer(Map<String, Object> root, String templatePath, String sourcePath) throws RuntimeException {
        OutputStream out = null;
        try {
            cfg.setClassForTemplateLoading(CreateJavaSourceServiceImpl.class, "");
            Template t = cfg.getTemplate(templatePath, "UTF-8");
            File file = new File(sourcePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            out = new FileOutputStream(new File(sourcePath));
            t.process(root, new OutputStreamWriter(out, "UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private String createJavaBeanName(String tableName){
        String beanNameTemp = JdbcUtils.convertUnderscoreNameToPropertyName(tableName.toLowerCase());
        return beanNameTemp.substring(0, 1).toUpperCase() + beanNameTemp.substring(1);
    }
    
    public String upperCase(String str) {  
        return str.substring(0, 1).toUpperCase() + str.substring(1);  
    }
    
    public String lowerCase(String str) {  
        return str.substring(0, 1).toLowerCase() + str.substring(1);  
    }

}
