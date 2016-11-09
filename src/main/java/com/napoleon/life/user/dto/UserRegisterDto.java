package com.napoleon.life.user.dto;

import com.google.code.kaptcha.Constants;
import com.napoleon.life.common.util.RSAUtils;
import com.napoleon.life.common.util.validator.Validator;
import com.napoleon.life.framework.resolver.SessionValue;

public class UserRegisterDto {

	/**
	 * 用户姓名
	 */
	@Validator(desc = "用户姓名", nullable = false, maxLength = 32)
	private String userName;
	
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
	 * 密码，密文
	 */
	@Validator(desc = "密码", minLength = 8, maxLength = 20, nullable = false)
	private String password;

	/**
	 * 确认密码，密文
	 */
	@Validator(desc = "确认密码", minLength = 8, maxLength = 20, nullable = false, dependesOn = "equalsPassword")
	private String confirmPassword;

	/**
	 * 验证码
	 */
	@Validator(desc = "验证码", nullable = false, dependesOn = "validateIdentifyCode")
	private String identifyCode;
	
	@SessionValue(key = Constants.KAPTCHA_SESSION_KEY)
	private String sessionScopeIdentifyCode;

	public boolean equalsPassword() {
		if(this.password != null && this.confirmPassword != null){
			//String decodeConfirmPassword = RSAUtils.decryptStringByJs(this.password);
			//String decodePassword = RSAUtils.decryptStringByJs(this.confirmPassword);
			//return decodeConfirmPassword.equals(decodePassword);
			return this.confirmPassword.equals(this.password);
		}else{
			return false;
		}
	}
	
	public boolean validateIdentifyCode(){
		return this.identifyCode != null && this.identifyCode.equalsIgnoreCase(sessionScopeIdentifyCode);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
}
