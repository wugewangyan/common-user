package com.napoleon.life.user.service;

import com.napoleon.life.user.bean.CommonUser;

public interface CommonUserService {
	/**
	 * 根据phoneNumber查看用户信息
	 * @param phoneNumber
	 * @return
	 */
	public CommonUser findByPhoneNumber(String phoneNumber);
	
	/**
	 * 根据userNo查看用户信息
	 * @param userNo
	 * @return
	 */
	public CommonUser findByUserNo(String userNo);
	
	/**
	 * 插入CommonUser信息
	 * @param userInfo
	 */
	public void insert(CommonUser userInfo);
	
}
