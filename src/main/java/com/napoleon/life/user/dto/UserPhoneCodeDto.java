package com.napoleon.life.user.dto;

import com.google.code.kaptcha.Constants;
import com.napoleon.life.common.util.validator.Validator;
import com.napoleon.life.framework.resolver.SessionValue;

public class UserPhoneCodeDto {

	/**
	 * 手机号码
	 */
	@Validator(desc = "手机号码", isPhone = true, nullable = false)
	private String phone;

	/**
	 * 验证码
	 */
	@Validator(desc = "验证码", nullable = false, dependesOn = "validateIdentifyCode")
	private String identifyCode;

	@SessionValue(key = Constants.KAPTCHA_SESSION_KEY)
	private String sessionScopeIdentifyCode;

	public boolean validateIdentifyCode() {
		return this.identifyCode != null
				&& this.identifyCode.equals(sessionScopeIdentifyCode);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentifyCode() {
		return identifyCode;
	}

	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}

	public String getSessionScopeIdentifyCode() {
		return sessionScopeIdentifyCode;
	}

	public void setSessionScopeIdentifyCode(String sessionScopeIdentifyCode) {
		this.sessionScopeIdentifyCode = sessionScopeIdentifyCode;
	}

}
