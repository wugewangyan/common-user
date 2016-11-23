package com.napoleon.life.user.createcode;

import java.util.List;

public class FacadeImplTemplate {
    private String facadePackageName;
    private String facadeFullName;
    
    private String facadeImplPackageName;
    private String facadeImplFullName;
    
    private String facadeName;
    private String facadeImplName;
    
    private String serviceName;
    private String serviceNameLowerCase;
    
    private List<String> imports;
    
    private String javaBeanName;
    private String editDtoBeanName;
    private String javaBeanNameToLower;
    
    private String entityNoToQueryUpcase;
    
	public String getFacadePackageName() {
		return facadePackageName;
	}
	public void setFacadePackageName(String facadePackageName) {
		this.facadePackageName = facadePackageName;
	}
	public String getFacadeFullName() {
		return facadeFullName;
	}
	public void setFacadeFullName(String facadeFullName) {
		this.facadeFullName = facadeFullName;
	}
	public String getFacadeImplPackageName() {
		return facadeImplPackageName;
	}
	public void setFacadeImplPackageName(String facadeImplPackageName) {
		this.facadeImplPackageName = facadeImplPackageName;
	}
	public String getFacadeImplFullName() {
		return facadeImplFullName;
	}
	public void setFacadeImplFullName(String facadeImplFullName) {
		this.facadeImplFullName = facadeImplFullName;
	}
	public String getFacadeName() {
		return facadeName;
	}
	public void setFacadeName(String facadeName) {
		this.facadeName = facadeName;
	}
	public String getFacadeImplName() {
		return facadeImplName;
	}
	public void setFacadeImplName(String facadeImplName) {
		this.facadeImplName = facadeImplName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceNameLowerCase() {
		return serviceNameLowerCase;
	}
	public void setServiceNameLowerCase(String serviceNameLowerCase) {
		this.serviceNameLowerCase = serviceNameLowerCase;
	}
	public List<String> getImports() {
		return imports;
	}
	public void setImports(List<String> imports) {
		this.imports = imports;
	}
	public String getJavaBeanName() {
		return javaBeanName;
	}
	public void setJavaBeanName(String javaBeanName) {
		this.javaBeanName = javaBeanName;
	}
	public String getEditDtoBeanName() {
		return editDtoBeanName;
	}
	public void setEditDtoBeanName(String editDtoBeanName) {
		this.editDtoBeanName = editDtoBeanName;
	}
	public String getJavaBeanNameToLower() {
		return javaBeanNameToLower;
	}
	public void setJavaBeanNameToLower(String javaBeanNameToLower) {
		this.javaBeanNameToLower = javaBeanNameToLower;
	}
	public String getEntityNoToQueryUpcase() {
		return entityNoToQueryUpcase;
	}
	public void setEntityNoToQueryUpcase(String entityNoToQueryUpcase) {
		this.entityNoToQueryUpcase = entityNoToQueryUpcase;
	}
}
