package com.napoleon.life.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.napoleon.life.common.persistence.GenericDaoDefault;
import com.napoleon.life.user.bean.CommonSerial;
import com.napoleon.life.user.dao.CommonSerialDao;

@Repository
public class CommonSerialDaoImpl extends GenericDaoDefault<CommonSerial> implements
		CommonSerialDao {

	@Override
	public Long getCommonSerialByIp(String ipAddr) {
		return (Long)this.queryOne("getCommonSerialByIp", ipAddr);
	}
}
