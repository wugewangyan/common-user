package com.napoleon.life.user.dto;

import com.google.code.kaptcha.Constants;
import com.napoleon.life.common.util.validator.Validator;
import com.napoleon.life.framework.resolver.SessionValue;

public class UserForgetPwdDto {

	/**
	 * 手机号码
	 */
	@Validator(desc = "手机号码", isPhone = true, nullable = false)
	private String phone;
	
	/**
	 * 手机验证码
	 */
	@Validator(desc = "手机验证码", nullable = false, minLength = 6, maxLength = 6)
	private String phoneCode;

	/**
	 * 新密码，密文
	 */
	@Validator(desc = "新密码", minLength = 8, maxLength = 20, nullable = false)
	private String newPassword;

	/**
	 * 确认密码，密文
	 */
	@Validator(desc = "确认密码", minLength = 8, maxLength = 20, nullable = false, dependesOn = "equalsPassword")
	private String confirmNewPassword;

	/**
	 * 验证码
	 */
	@Validator(desc = "验证码", nullable = false, dependesOn = "validateIdentifyCode")
	private String identifyCode;
	
	@SessionValue(key = Constants.KAPTCHA_SESSION_KEY)
	private String sessionScopeIdentifyCode;

	public boolean equalsPassword() {
		if(this.newPassword != null && this.confirmNewPassword != null){
			//String decodeConfirmPassword = RSAUtils.decryptStringByJs(this.password);
			//String decodePassword = RSAUtils.decryptStringByJs(this.confirmPassword);
			//return decodeConfirmPassword.equals(decodePassword);
			return this.newPassword.equals(this.confirmNewPassword);
		}else{
			return false;
		}
	}
	
	public boolean validateIdentifyCode(){
		return this.identifyCode != null && this.identifyCode.equalsIgnoreCase(sessionScopeIdentifyCode);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
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
