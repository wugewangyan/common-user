package com.napoleon.life.user.dto;

import com.napoleon.life.common.util.validator.Validator;
import com.napoleon.life.framework.enums.LoginSourceEnum;

public class UserLoginDto {

	/**
	 * 手机号码
	 */
	@Validator(desc = "手机号码", isPhone = true, nullable = false)
	private String phoneNumber;

	/**
	 * 密码
	 */
	@Validator(desc = "密码", minLength = 8, maxLength = 20, nullable = false)
	private String password;
	
	/**
	 * 登陆来源
	 */
	@Validator(desc = "来源", enumScope = LoginSourceEnum.class, nullable = false)
	private String source;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		// 可能前端过来的password需要解密：RSAUtils.decryptStringByJs(login.getP())
		this.password = password;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
