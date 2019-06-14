/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : wechattestdb

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-06-13 17:37:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(50) NOT NULL COMMENT '用户ID',
  `open_id` varchar(50) NOT NULL,
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `headimgurl` varchar(255) NOT NULL,
  `sex` tinyint(4) NOT NULL COMMENT '用户性别',
  `city` varchar(50) NOT NULL COMMENT '用户所在城市',
  `province` varchar(50) NOT NULL COMMENT '省份',
  `country` varchar(50) NOT NULL COMMENT '国家',
  `language` varchar(50) NOT NULL COMMENT '用户所用语言',
  `subscribe_time` datetime NOT NULL COMMENT '用户关注时间',
  `Latitude` decimal(10,7) NOT NULL COMMENT '用户所在地理位置的纬度',
  `Longitude` decimal(10,7) NOT NULL COMMENT '经度',
  `Precision` decimal(10,7) NOT NULL COMMENT '地理位置精度',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
