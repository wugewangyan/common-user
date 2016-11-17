package com.napoleon.life.user.createcode;

import java.util.List;

public class JavaBeanTemplate {
    private String packageName;
    private List<String> imports;
    private String className;
    private List<Entry> entrys;
    private String tableName;
    private String primaryKey;
    private String codeBase;
    private String javaBeanFullName;
    private String entityNoToQueryUpcase;
    private String entityNoToQuery;
    private String entityNoToQueryJdbc;
    

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Entry> getEntrys() {
        return entrys;
    }

    public void setEntrys(List<Entry> entrys) {
        this.entrys = entrys;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCodeBase() {
        return codeBase;
    }

    public void setCodeBase(String codeBase) {
        this.codeBase = codeBase;
    }

    public String getJavaBeanFullName() {
        return javaBeanFullName;
    }

    public void setJavaBeanFullName(String javaBeanFullName) {
        this.javaBeanFullName = javaBeanFullName;
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
