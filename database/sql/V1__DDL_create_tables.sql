-- -----------------------------------------------------
-- Table `t_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` VARCHAR(36) NOT NULL COMMENT 'uuid',
  `username` VARCHAR(32) NOT NULL COMMENT 'login username',
  `password` VARCHAR(64) NULL COMMENT 'login password',
  `create_dt` DATETIME NULL COMMENT 'create date',
  `update_dt` DATETIME NULL COMMENT 'update date',
  `pwdsalt` VARCHAR(36) NULL COMMENT 'Hash salt',
  `last_login_dt` DATETIME NULL COMMENT 'last login time',
  `name` VARCHAR(16) NULL COMMENT 'username',
  `mobile` VARCHAR(16) NULL COMMENT 'mobile',
  `gender` int(4) NOT NULL DEFAULT '0' COMMENT '默认0为男，1为女',
  `birthday` DATETIME NULL COMMENT '生日',
  `type` int(4) NOT NULL DEFAULT '0' COMMENT '默认0为老师，1为客户, 2为超管',
  `state` int(4) NOT NULL DEFAULT '0' COMMENT '默认0为激活，1为去激活，2为删除',
  `keyword` TEXT NULL COMMENT 'keyword',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `t_classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_classes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` VARCHAR(32) NOT NULL COMMENT 'uuid',
  `name` VARCHAR(32) NULL COMMENT 'class name',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'state',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'type',
  `create_dt` datetime DEFAULT NULL,
  `update_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL,
  `userid` int(11) NOT NULL COMMENT '用户ID',
  `roleid` bigint(20)  NOT NULL COMMENT '角色ID',
  `is_deleted` int(2) NOT NULL DEFAULT '0' COMMENT '默认0为可用，1为删除',
  `create_dt` datetime(3) DEFAULT NULL,
  `update_dt` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户-角色关系表';

CREATE TABLE `t_role_permission` (
  `id` bigint(20) NOT NULL,
  `roleid` bigint(20) NOT NULL COMMENT '角色ID',
  `permid` bigint(20) NOT NULL COMMENT '权限ID',
  `is_deleted` int(2) NOT NULL DEFAULT '0' COMMENT '默认0位可用，1为删除',
  `create_dt` datetime(3) DEFAULT NULL,
  `update_dt` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色-权限关系表';

CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `descinfo` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述',
  `is_deleted` int(2) NOT NULL DEFAULT '0' COMMENT '默认0为可用，1为删除',
  `create_dt` datetime(3) DEFAULT NULL,
  `update_dt` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL,
  `perm_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名称',
  `descinfo` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述',
  `is_deleted` int(2) NOT NULL DEFAULT '0' COMMENT '默认0可用, 1为删除',
  `create_dt` datetime(3) DEFAULT NULL,
  `update_dt` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限表';

-- -----------------------------------------------------
-- Table `t_customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` VARCHAR(36) NOT NULL COMMENT 'uuid',
  `no` int(11) NOT NULL COMMENT '编号',
  `kindergarten` VARCHAR(64) NULL COMMENT '幼儿园名称',
  `create_dt` DATETIME NULL COMMENT 'create date',
  `update_dt` DATETIME NULL COMMENT 'update date',
  `card_type` int(4) NOT NULL DEFAULT '0' COMMENT '默认0为创才，1为创智，2 bing卡',
  `fee` int(11) NOT NULL COMMENT '费用',
  `discount` int(11) NOT NULL COMMENT '折扣',
  `gift` VARCHAR(36) NULL COMMENT '礼物',
  `primary_class_hour` int(11) NOT NULL COMMENT '主课时',
  `secondary_class_hour` int(11) NOT NULL COMMENT '辅课时',
  `buy_dt` DATETIME NULL COMMENT '购买日期',
  `expire_dt` DATETIME NULL COMMENT '到期日期',
  `primary_learn_1` VARCHAR(32) NULL COMMENT '主修1',
  `primary_learn_2` VARCHAR(32) NULL COMMENT '主修2',
  `primary_learn_3` VARCHAR(32) NULL COMMENT '主修3',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `no_UNIQUE` (`no` ASC)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
