package com.napoleon.life.user.createcode;

public class UpdateEntry {
	private String column;
	private String javaColumn;
	private String updateJavaColumn;
	private String jdbcType;
	
	public UpdateEntry(){}

	public UpdateEntry(String column, String javaColumn,
			String updateJavaColumn, String jdbcType) {
		super();
		this.column = column;
		this.javaColumn = javaColumn;
		this.updateJavaColumn = updateJavaColumn;
		this.jdbcType = jdbcType;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getJavaColumn() {
		return javaColumn;
	}

	public void setJavaColumn(String javaColumn) {
		this.javaColumn = javaColumn;
	}

	public String getUpdateJavaColumn() {
		return updateJavaColumn;
	}

	public void setUpdateJavaColumn(String updateJavaColumn) {
		this.updateJavaColumn = updateJavaColumn;
	}
}
