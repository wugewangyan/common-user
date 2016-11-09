package com.napoleon.life.user.dao;

import com.napoleon.life.common.persistence.GenericDao;
import com.napoleon.life.user.bean.CommonSerial;


public interface CommonSerialDao extends GenericDao<CommonSerial> {

	public Long getCommonSerialByIp(String ipAddr);
	
}
