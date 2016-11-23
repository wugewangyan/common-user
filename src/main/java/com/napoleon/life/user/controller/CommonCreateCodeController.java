package com.napoleon.life.user.controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.napoleon.life.common.util.StringUtil;
import com.napoleon.life.exception.CommonException;
import com.napoleon.life.exception.CommonResultCode;
import com.napoleon.life.framework.base.BaseController;
import com.napoleon.life.framework.result.CommonRltUtil;
import com.napoleon.life.user.createcode.DaoImplTemplate;
import com.napoleon.life.user.createcode.DaoTemplate;
import com.napoleon.life.user.createcode.FacadeImplTemplate;
import com.napoleon.life.user.createcode.JavaBeanTemplate;
import com.napoleon.life.user.createcode.ServiceImplTemplate;
import com.napoleon.life.user.service.CommonCreateCodeService;

@Controller
@RequestMapping("/common/code")
public class CommonCreateCodeController extends BaseController{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CommonCreateCodeService codeService;
    

    @ResponseBody
    @RequestMapping(value = "/create_life", method = RequestMethod.GET)
    public String createLifeCode(String table_name) {
        Connection connection = null;
        
        try {
        	List<String> tableNames = new ArrayList<String>();
        	if(StringUtil.notEmpty(table_name)){
        		tableNames.add(table_name);
        	}else{
        		connection = this.dataSource.getConnection();
        		DatabaseMetaData metaData = connection.getMetaData();
        		ResultSet resultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});
            	
        		while(resultSet.next()){
        			tableNames.add(resultSet.getString(3));
        		}
        	}
    		
    		for(String tableName : tableNames){
    			JavaBeanTemplate javaBeanTemplate = this.codeService.createJavaBean(tableName);
	            DaoTemplate daoTemplate = this.codeService.createDao(javaBeanTemplate);
	            DaoImplTemplate daoImplTemplate = this.codeService.createDaoImpl(javaBeanTemplate, daoTemplate);
	            ServiceImplTemplate serviceImplTemplate = this.codeService.createServiceImpl(javaBeanTemplate, daoTemplate);
	            FacadeImplTemplate facadeImplTemplate = this.codeService.createFacade(javaBeanTemplate, serviceImplTemplate);
	            this.codeService.createController(javaBeanTemplate, facadeImplTemplate);
	            this.codeService.createMapper(javaBeanTemplate, daoTemplate, daoImplTemplate);
    		}
        
            return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS);
        } catch (Exception e) {
            throw new CommonException(e);
        }finally{
        	try{
            	if(connection != null){
            		connection.close();
            	}
        	}catch(Exception e){
        		throw new CommonException(e);
        	}
        }
    }
}
