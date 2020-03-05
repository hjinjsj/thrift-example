CREATE DATABASE IF NOT EXISTS xd_user_0;

USE xd_user_0;

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `name` varchar(255) NOT NULL COMMENT '用户名字',
  `mobile` varchar(16) NOT NULL DEFAULT '' COMMENT '用户手机号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY uid(`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE DATABASE IF NOT EXISTS xd_user_1;

USE xd_user_1;

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `name` varchar(255) NOT NULL COMMENT '用户名字',
  `mobile` varchar(16) NOT NULL DEFAULT '' COMMENT '用户手机号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否删除',
  PRIMARY KEY uid(`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;