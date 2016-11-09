package com.napoleon.life.user.enums;

public enum ActivateStatusEnum {

	ACTIVATE_NO(1, "未激活"),
	ACTIVATE_YES(2, "已激活");
	
	private Integer code;
	private String desc;
	
	private ActivateStatusEnum(Integer code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String toDesc(Integer code) {
		for (ActivateStatusEnum category : ActivateStatusEnum.values()) {
			if (category.getCode().equals(code)) {
				return category.getDesc();
			}
		}
		return null;
	}
	
	public static ActivateStatusEnum toEnum(Integer code) {
        for (ActivateStatusEnum category : ActivateStatusEnum.values()) {
            if (category.getCode().equals(code)) {
                    return category;
            }
        }
        return null;
    }
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
