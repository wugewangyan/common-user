package com.napoleon.life.user.createcode;

import java.util.List;

public class DaoTemplate {
    private String packageName;
    private List<String> imports;
    private String javaBeanName;
    private String daoName;
    private String daoFullName;
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

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String daoName) {
        this.daoName = daoName;
    }

    public String getDaoFullName() {
        return daoFullName;
    }

    public void setDaoFullName(String daoFullName) {
        this.daoFullName = daoFullName;
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
