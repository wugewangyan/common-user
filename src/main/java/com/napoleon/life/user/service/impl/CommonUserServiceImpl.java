package com.napoleon.life.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.napoleon.life.common.util.StringUtil;
import com.napoleon.life.exception.CommonException;
import com.napoleon.life.exception.CommonResultCode;
import com.napoleon.life.user.bean.CommonUser;
import com.napoleon.life.user.dao.CommonUserDao;
import com.napoleon.life.user.service.CommonUserService;


@Service
public class CommonUserServiceImpl implements CommonUserService {
	
	@Autowired
	private CommonUserDao userDao;
	
	public CommonUser findByPhoneNumber(String phoneNumber){
		return this.userDao.findByPhone(phoneNumber);
	}
	
	
	public CommonUser findByUserNo(String userNo){
		return this.userDao.findByUserNo(userNo);
	}
	
	@Override
	@Transactional
	public void insertOrUpdate(CommonUser userInfo) {
		if(userInfo != null){
			if(StringUtil.notEmpty(userInfo.getId())){
				this.userDao.update(userInfo);
			}else{
				this.userDao.add(userInfo);
			}
		}else{
			throw new CommonException(CommonResultCode.SYSTEM_ERR);
		}
	}
}
