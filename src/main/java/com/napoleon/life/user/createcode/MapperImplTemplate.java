package com.napoleon.life.user.createcode;

import java.util.List;

import com.napoleon.life.user.createcode.UpdateEntry;


public class MapperImplTemplate {
	private String nameSpace;
	private String javaBeanFullName;
	private String insert;
	private String update;
	private String delete;
	private String findById;
	private String getAll;
	private String baseColumnList;
	private List<UpdateEntry> updateEntrys;
	private String pkJavaName;
	private String pkName;
	private String pkJdbcType;
	private String pkNameUpdate;
	private String tableName;
    private String entityNoToQueryUpcase;
    private String getByEntityNo;
	
	public String getNameSpace() {
		return nameSpace;
	}
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	public String getJavaBeanFullName() {
		return javaBeanFullName;
	}
	public void setJavaBeanFullName(String javaBeanFullName) {
		this.javaBeanFullName = javaBeanFullName;
	}
	public String getInsert() {
		return insert;
	}
	public void setInsert(String insert) {
		this.insert = insert;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public String getFindById() {
		return findById;
	}
	public void setFindById(String findById) {
		this.findById = findById;
	}
	public String getGetAll() {
		return getAll;
	}
	public void setGetAll(String getAll) {
		this.getAll = getAll;
	}
	public String getPkJavaName() {
		return pkJavaName;
	}
	public void setPkJavaName(String pkJavaName) {
		this.pkJavaName = pkJavaName;
	}
	public String getPkName() {
		return pkName;
	}
	public void setPkName(String pkName) {
		this.pkName = pkName;
	}
	public List<UpdateEntry> getUpdateEntrys() {
		return updateEntrys;
	}
	public void setUpdateEntrys(List<UpdateEntry> updateEntrys) {
		this.updateEntrys = updateEntrys;
	}
	public String getPkNameUpdate() {
		return pkNameUpdate;
	}
	public void setPkNameUpdate(String pkNameUpdate) {
		this.pkNameUpdate = pkNameUpdate;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPkJdbcType() {
		return pkJdbcType;
	}
	public void setPkJdbcType(String pkJdbcType) {
		this.pkJdbcType = pkJdbcType;
	}
	public String getBaseColumnList() {
		return baseColumnList;
	}
	public void setBaseColumnList(String baseColumnList) {
		this.baseColumnList = baseColumnList;
	}
	public String getEntityNoToQueryUpcase() {
		return entityNoToQueryUpcase;
	}
	public void setEntityNoToQueryUpcase(String entityNoToQueryUpcase) {
		this.entityNoToQueryUpcase = entityNoToQueryUpcase;
	}
	public String getGetByEntityNo() {
		return getByEntityNo;
	}
	public void setGetByEntityNo(String getByEntityNo) {
		this.getByEntityNo = getByEntityNo;
	}
}
