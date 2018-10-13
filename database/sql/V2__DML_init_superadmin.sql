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