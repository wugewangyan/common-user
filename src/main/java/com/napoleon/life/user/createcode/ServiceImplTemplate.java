package com.napoleon.life.user.createcode;

import java.util.List;

public class ServiceImplTemplate {
    private String servicePackageName;
    private String serviceFullName;
    private String serviceImplPackageName;
    private String serviceImplFullName;
    private List<String> imports;
    private String serviceName;
    private String serviceImplName;
    private String javaBeanName;
    private String daoBeanName;
    private String daoBeanNameLowerCase;
    private String entityNoToQueryUpcase;
    private String entityNoToQuery;
    private String entityNoToQueryJdbc;

    public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

    public String getDaoBeanName() {
        return daoBeanName;
    }

    public void setDaoBeanName(String daoBeanName) {
        this.daoBeanName = daoBeanName;
    }

	public String getDaoBeanNameLowerCase() {
		return daoBeanNameLowerCase;
	}

	public void setDaoBeanNameLowerCase(String daoBeanNameLowerCase) {
		this.daoBeanNameLowerCase = daoBeanNameLowerCase;
	}

	public String getEntityNoToQueryUpcase() {
		return entityNoToQueryUpcase;
	}

	public void setEntityNoToQueryUpcase(String entityNoToQueryUpcase) {
		this.entityNoToQueryUpcase = entityNoToQueryUpcase;
	}

	public String getEntityNoToQuery() {
		return entityNoToQuery;
	}

	public void setEntityNoToQuery(String entityNoToQuery) {
		this.entityNoToQuery = entityNoToQuery;
	}

	public String getEntityNoToQueryJdbc() {
		return entityNoToQueryJdbc;
	}

	public void setEntityNoToQueryJdbc(String entityNoToQueryJdbc) {
		this.entityNoToQueryJdbc = entityNoToQueryJdbc;
	}


	public String getServicePackageName() {
		return servicePackageName;
	}

	public void setServicePackageName(String servicePackageName) {
		this.servicePackageName = servicePackageName;
	}

	public String getServiceImplPackageName() {
		return serviceImplPackageName;
	}

	public void setServiceImplPackageName(String serviceImplPackageName) {
		this.serviceImplPackageName = serviceImplPackageName;
	}

	public String getServiceFullName() {
		return serviceFullName;
	}

	public void setServiceFullName(String serviceFullName) {
		this.serviceFullName = serviceFullName;
	}

	public String getServiceImplFullName() {
		return serviceImplFullName;
	}

	public void setServiceImplFullName(String serviceImplFullName) {
		this.serviceImplFullName = serviceImplFullName;
	}

	public String getServiceImplName() {
		return serviceImplName;
	}

	public void setServiceImplName(String serviceImplName) {
		this.serviceImplName = serviceImplName;
	}
	
}
