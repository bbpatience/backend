LOCK TABLES `t_user` WRITE;

INSERT INTO `t_user` (
    `uid`,
	`username`,
	`password`,
	`create_dt`,
	`update_dt`,
	`name`,
	`pwdsalt`,
	`type`
	)
VALUES (
  ${superadmin.uid},
	'superadmin',
	${superadmin.password},
	UTC_TIMESTAMP(),
	UTC_TIMESTAMP(),
	'superadmin',
	${superadmin.salt},
	2
	)
;

UNLOCK TABLES;

# t_role
INSERT INTO `t_role` VALUES (1, 'superadmin', '超级管理员', 0, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP);
INSERT INTO `t_role` VALUES (2, 'staff', '员工', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO `t_role` VALUES (3, 'userBean', '用户', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

# t_permission
INSERT INTO `t_permission` VALUES (1, '*', '全部', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

# t_role_permission
INSERT INTO `t_role_permission` VALUES (1, 1, 1, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

# t_user_role
INSERT INTO `t_user_role` VALUES (1, 1, 1, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);