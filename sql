CREATE TABLE `common_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_no` varchar(64) NOT NULL DEFAULT '' COMMENT '用户编号',
  `user_name` varchar(32) NOT NULL COMMENT '用户姓名',
  `email` varchar(32) NOT NULL COMMENT '邮箱账号',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码，密文',
  `sex` varchar(10) DEFAULT '' COMMENT '性别',
  `phone` varchar(64) DEFAULT '' COMMENT '电话号码，密文',
  `activate_status` tinyint(2) NOT NULL COMMENT '1:未激活，2已经激活',
  `status` tinyint(2) NOT NULL COMMENT '1：正常，2.冻结，3注销',
  `address` varchar(128) DEFAULT NULL COMMENT '地址',
  `header_img` varchar(256) DEFAULT NULL COMMENT '头像存放地址',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息';


CREATE TABLE `common_serial` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `machine_addr` varchar(32) NOT NULL COMMENT '机器地址',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_machineaddr` (`machine_addr`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;