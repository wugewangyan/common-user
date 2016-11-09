package com.napoleon.life.user.bean;

import com.napoleon.life.common.persistence.Entity;



public class CommonSerial implements Entity<Long> {

	private static final long serialVersionUID = 4589775798721971132L;

	/**
	 * 自增主键
	 */
	private Long id;

	/**
	 * 机器地址
	 */
	private String machineAddr;

	/**
	 * 备注
	 */
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMachineAddr() {
		return machineAddr;
	}

	public void setMachineAddr(String machineAddr) {
		this.machineAddr = machineAddr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}