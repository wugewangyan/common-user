package com.napoleon.life.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.napoleon.life.common.persistence.GenericDaoDefault;
import com.napoleon.life.user.bean.CommonUser;
import com.napoleon.life.user.dao.CommonUserDao;

@Repository
public class CommonUserDaoImpl extends GenericDaoDefault<CommonUser> implements
		CommonUserDao {
	
	@Override
	public CommonUser findByUserNo(String userNo) {
		return (CommonUser)super.queryOne("findByUserNo", userNo);
	}
	
	@Override
	public CommonUser findByPhone(String phone) {
		return (CommonUser)super.queryOne("findByPhone", phone);
	}
}
