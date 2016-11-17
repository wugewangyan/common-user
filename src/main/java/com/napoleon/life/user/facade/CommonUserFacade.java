package com.napoleon.life.user.facade;

import com.napoleon.life.framework.base.BaseDto;
import com.napoleon.life.user.dto.UserEditDto;
import com.napoleon.life.user.dto.UserForgetPwdDto;
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

	/**
	 * 用户忘记密码
	 * @param forgetPwdInfo
	 * @return
	 */
	public String forgetPwd(UserForgetPwdDto forgetPwdInfo);

	/**
	 * 用户退出
	 * @param loginOutInfo
	 * @return
	 */
	public String loginOut(BaseDto loginOutInfo);

	/**
	 * 编辑用户信息
	 * @param userEditInfo
	 * @return
	 */
	public String editUser(UserEditDto userEditInfo);

	/**
	 * 查看用户信息
	 * @param viewUserInfo
	 * @return
	 */
	public String viewUser(BaseDto viewUserInfo);
	
}
