package com.napoleon.life.user.controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.napoleon.life.common.util.StringUtil;
import com.napoleon.life.exception.CommonException;
import com.napoleon.life.framework.base.BaseController;
import com.napoleon.life.framework.result.CommonRltUtil;
import com.napoleon.life.user.code.UserModelCode;
import com.napoleon.life.user.createcode.JavaBeanTemplate;
import com.napoleon.life.user.service.CommonCreateCodeService;

@Controller
@RequestMapping("/common/code")
public class CommonCreateCodeController extends BaseController{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CommonCreateCodeService codeService;
    

    @ResponseBody
    @RequestMapping(value = "/create_life", method = RequestMethod.POST)
    public String createLifeCode(String[] table_name, String is_contain) {
        Connection connection = null;
        // 如果is_contain为YES，
        try {
        	List<String> inputTables = Arrays.asList(table_name);
        	
        	List<String> tableNames = new ArrayList<String>();
        	if("YES".equalsIgnoreCase(is_contain)){
        		tableNames.addAll(inputTables);
        	}else{
        		connection = this.dataSource.getConnection();
        		DatabaseMetaData metaData = connection.getMetaData();
        		ResultSet resultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});
            	
        		while(resultSet.next()){
        			if("NO".equalsIgnoreCase(is_contain)){
        				String db_table_name = resultSet.getString(3);
        				if(inputTables != null && inputTables.contains(db_table_name.toLowerCase())){
        					continue;
        				}
        				
        				tableNames.add(db_table_name);
        			}
        			
        		}
        	}
    		
    		for(String tableName : tableNames){
    			System.out.println("to product table :" + tableName);
    			JavaBeanTemplate javaBeanTemplate = this.codeService.createJavaBean(tableName);
	            //DaoTemplate daoTemplate = this.codeService.createDao(javaBeanTemplate);
	            //DaoImplTemplate daoImplTemplate = this.codeService.createDaoImpl(javaBeanTemplate, daoTemplate);
	            //ServiceImplTemplate serviceImplTemplate = this.codeService.createServiceImpl(javaBeanTemplate, daoTemplate);
	            //FacadeImplTemplate facadeImplTemplate = this.codeService.createFacade(javaBeanTemplate, serviceImplTemplate);
	            //this.codeService.createController(javaBeanTemplate, facadeImplTemplate);
	            this.codeService.createMapper(javaBeanTemplate, null, null);
    		}
        
            return CommonRltUtil.createCommonRltToString(UserModelCode.USER_SUCCESS);
        } catch (Exception e) {
            throw new CommonException(UserModelCode.PRODUCT_CODE_EXCEPTION, e);
        }finally{
        	try{
            	if(connection != null){
            		connection.close();
            	}
        	}catch(Exception e){
        		throw new CommonException(UserModelCode.PRODUCT_CODE_EXCEPTION, e);
        	}
        }
    }
}
