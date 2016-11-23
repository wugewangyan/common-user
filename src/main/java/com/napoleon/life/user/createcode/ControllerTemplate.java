package com.napoleon.life.user.createcode;

import java.util.List;

public class ControllerTemplate {
	
	private String controllerPackageName;
	private String controllerName;
    private String facadeFullName;
    private String facadeNameLowerCase;
    private String facadeName;
    
    private String requestPre;
    private String requestSuffix;
    
    private List<String> imports;
    
    private String javaBeanName;
    private String editDtoBeanName;
    
	public String getControllerPackageName() {
		return controllerPackageName;
	}
	public void setControllerPackageName(String controllerPackageName) {
		this.controllerPackageName = controllerPackageName;
	}
	public String getControllerName() {
		return controllerName;
	}
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}
	public String getFacadeFullName() {
		return facadeFullName;
	}
	public void setFacadeFullName(String facadeFullName) {
		this.facadeFullName = facadeFullName;
	}
	public String getFacadeNameLowerCase() {
		return facadeNameLowerCase;
	}
	public void setFacadeNameLowerCase(String facadeNameLowerCase) {
		this.facadeNameLowerCase = facadeNameLowerCase;
	}
	public String getFacadeName() {
		return facadeName;
	}
	public void setFacadeName(String facadeName) {
		this.facadeName = facadeName;
	}
	public String getRequestPre() {
		return requestPre;
	}
	public void setRequestPre(String requestPre) {
		this.requestPre = requestPre;
	}
	public String getRequestSuffix() {
		return requestSuffix;
	}
	public void setRequestSuffix(String requestSuffix) {
		this.requestSuffix = requestSuffix;
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
}
