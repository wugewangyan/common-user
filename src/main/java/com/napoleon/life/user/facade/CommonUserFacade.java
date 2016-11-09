package com.napoleon.life.user.facade;

import com.napoleon.life.user.dto.UserLoginDto;
import com.napoleon.life.user.dto.UserRegisterDto;



public interface CommonUserFacade {
	
	/**
	 * 获取手机验证码
	 * @param phone
	 * @return
	 */
	public String getPhoneCode(String phone);

	/**
	 * 用户注册
	 * @param registerInfo
	 * @return
	 */
	public String register(UserRegisterDto registerInfo);

	/**
	 * 用户登陆
	 * @param loginInfo
	 * @return
	 */
	public String login(UserLoginDto loginInfo);
	
}
