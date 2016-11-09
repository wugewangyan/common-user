package com.napoleon.life.user.enums;

public enum UserStatusEnum {

	STATUS_NORMAL(1, "正常"),
	STATUS_FROZEN(2, "冻结"),
	STATUS_CANCEL(3, "注销");

	private Integer code;
	private String desc;
	
	private UserStatusEnum(Integer code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String toDesc(Integer code) {
		for (UserStatusEnum category : UserStatusEnum.values()) {
			if (category.getCode().equals(code)) {
				return category.getDesc();
			}
		}
		return null;
	}
	
	public static UserStatusEnum toEnum(Integer code) {
        for (UserStatusEnum category : UserStatusEnum.values()) {
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
