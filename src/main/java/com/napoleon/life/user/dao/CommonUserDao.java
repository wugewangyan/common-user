package com.napoleon.life.user.dao;

import com.napoleon.life.common.persistence.GenericDao;
import com.napoleon.life.user.bean.CommonUser;

public interface CommonUserDao extends GenericDao<CommonUser> {
	
	/**
	 * 通过userNo查找用户信息
	 * @param userNo
	 * @return
	 */
	public CommonUser findByUserNo(String userNo);
	
	/**
	 * 通过phone查找用户信息
	 * @param phone
	 * @return
	 */
	public CommonUser findByPhone(String phone);
	
}
