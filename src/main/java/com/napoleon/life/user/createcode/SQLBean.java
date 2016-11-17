package com.napoleon.life.user.createcode;

import java.util.List;

import com.napoleon.life.user.constants.Constants;


public class SQLBean {
	
	private String inertSql;
	private String updateSql;
	private String deleteSql;
	private String findAllSql;
	private String findByIdSql;
	private String findByPageSql;
	private String baseColumnList;
	private List<String> columnNames;  //该表的列名
	private List<Integer> columnTypes;  // 列类型
	private List<String> decimalDigits;  // 类精度，用于生成代码是属性类型的判断
	private List<String> javaBeanColumns;   // javaBean字段名
	private List<String> remarks;  // 字段注释
	private List<String> jdbcTypes;
	private String pkName;  // 主键名
	private String tableName; // 表名
	private String pkBeanName;  // javaBean主键字段
	private String pkJdbcType;  // 主键的jdbcType
	
	private String selectNames; 
	
	public List<String> getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}
	public List<Integer> getColumnTypes() {
		return columnTypes;
	}
	public void setColumnTypes(List<Integer> columnTypes) {
		this.columnTypes = columnTypes;
	}
	public List<String> getDecimalDigits() {
		return decimalDigits;
	}
	public void setDecimalDigits(List<String> decimalDigits) {
		this.decimalDigits = decimalDigits;
	}
	public String getPkName() {
		return pkName;
	}
	public void setPkName(String pkName) {
		this.pkName = pkName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getInertSql() {
		return inertSql;
	}
	public String getUpdateSql() {
		return updateSql;
	}
	public String getDeleteSql() {
		return deleteSql;
	}
	public String getFindAllSql() {
		return findAllSql;
	}
	public String getFindByIdSql() {
		return findByIdSql;
	}
	public String getFindByPageSql() {
		return findByPageSql;
	}
	public void setSelectNames(String selectNames) {
		this.selectNames = selectNames;
	}
	public List<String> getJavaBeanColumns() {
		return javaBeanColumns;
	}
	public void setJavaBeanColumns(List<String> javaBeanColumns) {
		this.javaBeanColumns = javaBeanColumns;
	}
	public String getPkBeanName() {
		return pkBeanName;
	}
	public void setPkBeanName(String pkBeanName) {
		this.pkBeanName = pkBeanName;
	}
	public List<String> getRemarks() {
		return remarks;
	}
	public void setRemarks(List<String> remarks) {
		this.remarks = remarks;
	}
	
	public List<String> getJdbcTypes() {
		return jdbcTypes;
	}
	public void setJdbcTypes(List<String> jdbcTypes) {
		this.jdbcTypes = jdbcTypes;
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
	
	public void doCreateSQL(){
		this.setSaveSql();
		this.setUpdateSql();
		this.setRemoveSql();
		this.setFindByIdSql();
		this.setFindAllSql();
		this.setFindAllByPageSql();
	} 
	
	
	private void setSaveSql() {
		StringBuffer sql = new StringBuffer(Constants.INSERT_PRE_SQL);
		StringBuffer paramList = new StringBuffer(Constants.INSERT_VALUES_SQL);
		
		sql.append(this.tableName).append(Constants.LEFT_BRACES); 
		
		
		if(this.columnNames != null && this.columnNames.size() > 0){
			for(int i = 0; i < columnNames.size(); i++){
				String column = columnNames.get(i);
				String javaBeanColumn = this.javaBeanColumns.get(i);
				String jdbcType = this.jdbcTypes.get(i);
				if(!this.getPkName().equals(column)){
					sql.append(column).append(Constants.COMMA);
					paramList.append("#{").append(javaBeanColumn).append(", jdbcType=").append(jdbcType).append("}").append(Constants.COMMA);
				}
				//paramList.append(Constants.PARAMS_FLAG).append(Constants.COMMA);
			}
		}
		
		paramList = paramList.deleteCharAt(paramList.lastIndexOf(Constants.COMMA)).append(Constants.RIGHT_BRACES);
		this.inertSql = sql.deleteCharAt(sql.lastIndexOf(Constants.COMMA)).append(Constants.RIGHT_BRACES).append(paramList).toString();
	}
	
	private void setUpdateSql() {
		StringBuffer sql = new StringBuffer(Constants.UPDATE_PRE_SQL).append(this.tableName).append(Constants.UPDATE_SET_SQL);
		
		String pkSql = "";
		if(this.columnNames != null && this.columnNames.size() > 0){
			for(int i = 0; i < columnNames.size(); i++){
				String column = columnNames.get(i);
				String javaBeanColumn = this.javaBeanColumns.get(i);
				if(!this.getPkName().equals(column)){
					//sql.append(column).append(Constants.UPDATE_PARAMS_FALG);
					sql.append(column).append(" = #{").append(javaBeanColumn).append("}, ");
				}else{
					pkSql = " = #{" + javaBeanColumn + "}, ";
				}
			}
		}
		
//		sql = sql.deleteCharAt(sql.lastIndexOf(Constants.COMMA))
//							.append(Constants.WHERE_SQL).append(this.getPkName())
//							.append(Constants.UPDATE_PARAMS_FALG);
		sql = sql.deleteCharAt(sql.lastIndexOf(Constants.COMMA))
				.append(Constants.WHERE_SQL).append(this.getPkName())
				.append(pkSql);
		this.updateSql = sql.deleteCharAt(sql.lastIndexOf(Constants.COMMA)).toString();
	}
	
	private void setRemoveSql() {
//		this.deleteSql = new StringBuffer(Constants.DELETE_PRE_SQL).append(this.getTableName()).append(Constants.WHERE_SQL)
//				.append(this.getPkName()).append(Constants.EQUAL_PARAMS_FALG).toString();
		this.deleteSql = new StringBuffer(Constants.DELETE_PRE_SQL).append(this.getTableName()).append(Constants.WHERE_SQL)
				.append(this.getPkName()).append(" = #{").append(this.getPkBeanName()).append(", jdbcType=").append(this.getPkJdbcType()).append("}").toString();
	}
	
	private void setFindAllSql() {
		if(selectNames == null){
			this.selectNames = getSelectNames();
		}
		
//		this.findAllSql = new StringBuffer(Constants.SELECT_PRE_SQL).append(this.selectNames).append(Constants.FROM_SQL)
//				.append(this.tableName).toString();
		this.findAllSql = new StringBuffer(Constants.SELECT_PRE_SQL).append("<include refid=\"Base_Column_List\" />").append(Constants.FROM_SQL)
				.append(this.tableName).toString();
	}
	
	private void setFindAllByPageSql() {
		if(this.findAllSql == null){
			this.setFindAllSql();
		}
		
//		this.findByPageSql = new StringBuffer(Constants.PAGING_PRE_SQL).append(this.findAllSql)
//				.append(Constants.PAGING_END_SQL).toString();
		
		this.findByPageSql = new StringBuffer(this.findAllSql).append(" ORDER BY ").append(this.pkName).append(Constants.PAGEING_MYSQL).toString();
	}
	
	private void setFindByIdSql() {
		if(selectNames == null){
			this.selectNames = getSelectNames();
		}
		
//		this.findByIdSql = new StringBuffer(Constants.SELECT_PRE_SQL).append(this.selectNames).append(Constants.FROM_SQL)
//				.append(this.tableName).append(Constants.WHERE_SQL).append(this.pkName)
//				.append(Constants.EQUAL_PARAMS_FALG).toString();
		
//		this.findByIdSql = new StringBuffer(Constants.SELECT_PRE_SQL).append(this.selectNames).append(Constants.FROM_SQL)
//				.append(this.tableName).append(Constants.WHERE_SQL).append(this.pkName)
//				.append(" = #{").append(this.getPkBeanName()).append(", jdbcType=").append(this.getPkJdbcType()).append("}").toString();
		this.findByIdSql = new StringBuffer(Constants.SELECT_PRE_SQL).append("<include refid=\"Base_Column_List\" />").append(Constants.FROM_SQL)
				.append(this.tableName).append(Constants.WHERE_SQL).append(this.pkName)
				.append(" = #{").append(this.getPkBeanName()).append(", jdbcType=").append(this.getPkJdbcType()).append("}").toString();
	}
	
	private String getSelectNames(){
		StringBuffer sql = new StringBuffer();
		
		if(this.columnNames != null && this.columnNames.size() > 0){
			for(String column : this.columnNames){
				if("desc".equals(column) || "order".equals(column)){
					sql.append("`").append(column).append("`").append(Constants.COMMA);
				}else{
					sql.append(column).append(Constants.COMMA);
				}
			}
		}
		
		String baseColumnList = sql.substring(0, sql.lastIndexOf(Constants.COMMA)).toString();
		this.baseColumnList = baseColumnList;
		return baseColumnList;
	}
}
