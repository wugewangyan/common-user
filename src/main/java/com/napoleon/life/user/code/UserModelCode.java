package com.napoleon.life.user.code;

import com.napoleon.life.exception.ModelCodeInterface;


public enum UserModelCode implements ModelCodeInterface {
	
	USER_SUCCESS("USER-0001", "USER_SUCCESS", "成功"),
	USER_ERROR("USER-9999", "USER_ERROR", "系统错误"),
	USER_PHONE_NUMBER_HAS_REGISTER("USER-0002", "USER_PHONE_NUMBER_HAS_REGISTER", "该手机号码已经被注册，请登陆"),
	USER_PHONE_CODE_EXPIRED("USER-0003", "USER_PHONE_CODE_EXPIRED", "手机验证码已经失效,请重新获取"),
	USER_PHONE_CODE_WRONG("USER-0004", "USER_PHONE_CODE_WRONG", "手机验证码错误"),
	USER_ACCOUNT_NOT_EXIST("USER-0005", "USER_ACCOUNT_NOT_EXIST", "用户不存在，请注册"),
	USER_PASSWORD_WRONG("USER-0006", "USER_PASSWORD_WRONG", "密码错误"),
	USER_ACCOUNT_NOT_ACTIVATE("USER-0007", "USER_ACCOUNT_NOT_ACTIVATE", "账号未激活,请激活账户后重新登陆"),
	USER_ACCOUNT_STATUS_INVALID("USER-0008", "USER_ACCOUNT_STATUS_INVALID", "账号被冻结或注销"),
	USER_REJECT_SOURCE("USER-0009", "USER_REJECT_SOURCE_LOGIN", "未知的来源"),
	USER_SEND_PHONE_CODE_FREQUENTLY("USER-0010", "USER_SEND_PHONE_CODE_FREQUENTLY", "发送手机验证码过于频繁，请稍后再试"),
	USER_SEND_PHONE_CODE_ERROR("USER-0011", "USER_CREATE_PHONE_CODE_ERROR", "发送手机验证码失败"),
	USER_DATE_FORMAT_ERROR("USER-0012", "USER_DATE_FORMAT_ERROR", "时间格式化错误"),
	USER_WEIGHTINFO_NOT_FOUND("USER-0013", "USER_WEIGHTINFO_NOT_FOUND", "您的体重信息不存在"), 
	USER_NOT_AUTH_TO_EDIT("USER-0014", "USER_NOT_AUTH_TO_EDIT", "您没有权限编辑该信息"),
	USER_OP_TYPE_NOT_SUPPORT("USER-0015", "USER_OP_TYPE_NOT_SUPPORT", "不支持的操作类型"),
	USER_WAISTINFO_NOT_FOUND("USER-0016", "USER_WAISTINFO_NOT_FOUND", "您的腰围信息不存在"),
	USER_COST_DETAIL_NOT_FOUND("USER-0017", "USER_COST_DETAIL_NOT_FOUND", "您的消费详情信息不存在"),
	USER_COST_TYPE_NOT_FOUND("USER-0018", "USER_COST_TYPE_NOT_FOUND", "消费类型不存在"),
	USER_COST_ORDER_NOT_FOUND("USER-0019", "USER_COST_ORDER_NOT_FOUND", "消费订单不存在"), 
	
	PRODUCT_CODE_EXCEPTION("200016", "PRODUCT_CODE_EXCEPTION", "代码生成出现异常"),
	;
	
	private final String code;
	private final String message;
	private final String chineseMessage;
	
	private UserModelCode(String code, String message, String chineseMessage){
		this.code = code;
		this.message = message;
		this.chineseMessage = chineseMessage;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getChineseMessage() {
		return chineseMessage;
	}
}
