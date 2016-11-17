package com.napoleon.life.user.createcode;

public class Entry {
	private String type;
	private String value;
	private Class<?> clazz;
	private String remark;
	
	public Entry(){}
	
	public Entry(String type, String value){
		this.type = type;
		this.value = value;
	}
	
	
	public Entry(String type, String value, Class<?> clazz, String remark) {
		super();
		this.type = type;
		this.value = value;
		this.clazz = clazz;
		this.remark = remark;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
