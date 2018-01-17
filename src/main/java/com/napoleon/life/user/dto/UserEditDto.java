package com.napoleon.life.user.dto;

import com.napoleon.life.common.util.validator.Validator;
import com.napoleon.life.framework.base.BaseDto;
import com.napoleon.life.user.enums.UserSexEnum;

public class UserEditDto extends BaseDto {

	@Validator(desc = "用户姓名", nullable = true, maxLength = 32)
	private String newUserName;
	
	@Validator(desc = "用户邮箱", nullable = true, maxLength = 32, isEmail = true)
	private String userEmail;
	
	@Validator(desc = "用户性别", nullable = true, enumScope = UserSexEnum.class)
	private String userSex;
	
	@Validator(desc = "用户身高", nullable = true, maxLength = 3, isInteger = true)
	private Integer userHeight;
	
	@Validator(desc = "用户住址", nullable = true, maxLength = 128)
	private String userAddress;

	@Validator(desc = "出生年月", nullable = true, isLong = true)
	private Long userBirthday;

	public String getNewUserName() {
		return newUserName;
	}

	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public Integer getUserHeight() {
		return userHeight;
	}

	public void setUserHeight(Integer userHeight) {
		this.userHeight = userHeight;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Long getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Long userBirthday) {
		this.userBirthday = userBirthday;
	}
}
