package com.napoleon.life.user.service;

import com.napoleon.life.user.createcode.DaoImplTemplate;
import com.napoleon.life.user.createcode.DaoTemplate;
import com.napoleon.life.user.createcode.JavaBeanTemplate;
import com.napoleon.life.user.createcode.MapperImplTemplate;
import com.napoleon.life.user.createcode.ServiceImplTemplate;

public interface CommonCreateCodeService {

    public JavaBeanTemplate createJavaBean(String tableName) throws RuntimeException;
    
    
    public DaoTemplate createDao(JavaBeanTemplate javaBeanTemplate) throws RuntimeException;
    
    
    public DaoImplTemplate createDaoImpl(JavaBeanTemplate javaBeanTemplate, DaoTemplate daoTemplate)
            throws RuntimeException;
    
    public ServiceImplTemplate createServiceImpl(JavaBeanTemplate javaBeanTemplate, DaoTemplate daoTemplate)
            throws RuntimeException;
    
    public MapperImplTemplate createMapper(JavaBeanTemplate javaBeanTemplate, DaoTemplate daoTemplate,
            DaoImplTemplate daoImplTemplate) throws RuntimeException;
}
