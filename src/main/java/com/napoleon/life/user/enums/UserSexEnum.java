package com.napoleon.life.user.enums;

public enum UserSexEnum {

	MALE("male", "男"),
	FAMALE("female", "女");
	
	private String code;
	private String desc;
	
	private UserSexEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String toDesc(String code) {
		for (UserSexEnum category : UserSexEnum.values()) {
			if (category.getCode().equals(code)) {
				return category.getDesc();
			}
		}
		return null;
	}
	
	public static UserSexEnum toEnum(String code) {
        for (UserSexEnum category : UserSexEnum.values()) {
            if (category.getCode().equals(code)) {
                    return category;
            }
        }
        return null;
    }
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
