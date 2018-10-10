SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Table `t_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_user` (
  `username` VARCHAR(32) NOT NULL COMMENT '登录用户名',
  `password` VARCHAR(64) NULL COMMENT '登录密码',
  `create_dt` DATETIME NULL COMMENT '创建日期',
  `update_dt` DATETIME NULL COMMENT '更新日期',
  `pwdsalt` VARCHAR(36) NULL COMMENT 'Hash密码用的盐',
  `last_login_dt` DATETIME NULL,
  `_uid` VARCHAR(32) NOT NULL COMMENT 'uuid',
  PRIMARY KEY (`_uid`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `t_classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_classes` (
  `_uid` VARCHAR(36) NOT NULL COMMENT "uuid",
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '类型',
  `create_dt` datetime DEFAULT NULL,
  `update_dt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
