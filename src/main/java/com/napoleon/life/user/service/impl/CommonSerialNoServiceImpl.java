package com.napoleon.life.user.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.napoleon.life.common.util.NetUtil;
import com.napoleon.life.common.util.SerialNoGenerator;
import com.napoleon.life.user.bean.CommonSerial;
import com.napoleon.life.user.dao.CommonSerialDao;
import com.napoleon.life.user.service.CommonSerialNoService;

@Component
public class CommonSerialNoServiceImpl implements CommonSerialNoService {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonSerialNoServiceImpl.class);

	@Autowired
	private CommonSerialDao commonSerialDao;

	private SerialNoGenerator guid;

	@PostConstruct
	public void init() {
		String ipAddr = NetUtil.getLocalHost();
		logger.info("the local ip : {}", ipAddr);
		Long workerId = this.commonSerialDao.getCommonSerialByIp(ipAddr);
		if (workerId == null) {
			CommonSerial lifeSerial = new CommonSerial();
			lifeSerial.setMachineAddr(ipAddr);
			this.commonSerialDao.add(lifeSerial);
			workerId = lifeSerial.getId();
		}

		logger.info("The worker id : {}", workerId);
		guid = new SerialNoGenerator(workerId);
	}

	@PreDestroy
	public void destory() {
		logger.info("The lifeSerialNoService has bean destory");
	}

	public String getSerialNo(String command) {
		return command + guid.nextNo();
	}
}
