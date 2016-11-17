package com.napoleon.life.user.createcode;

import java.util.List;

public class DaoImplTemplate {
    private String packageName;
    private List<String> imports;
    private String javaBeanName;
    private String daoBeanName;
    private String daoImplBeanName;
    private String daoImplFullBeanName;
    
    private String entityNoToQueryUpcase;
    private String entityNoToQuery;
    private String entityNoToQueryJdbc;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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

    public String getDaoImplBeanName() {
        return daoImplBeanName;
    }

    public void setDaoImplBeanName(String daoImplBeanName) {
        this.daoImplBeanName = daoImplBeanName;
    }

    public String getDaoImplFullBeanName() {
        return daoImplFullBeanName;
    }

    public void setDaoImplFullBeanName(String daoImplFullBeanName) {
        this.daoImplFullBeanName = daoImplFullBeanName;
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

}
