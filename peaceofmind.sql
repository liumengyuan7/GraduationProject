/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : peaceofmind

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2021-01-28 19:14:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tabel_replymsg`
-- ----------------------------
DROP TABLE IF EXISTS `tabel_replymsg`;
CREATE TABLE `tabel_replymsg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) DEFAULT NULL,
  `from_id` int(11) DEFAULT NULL,
  `to_id` int(11) DEFAULT NULL,
  `reply_content` varchar(255) DEFAULT NULL,
  `reply_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tabel_replymsg
-- ----------------------------

-- ----------------------------
-- Table structure for `table_collect`
-- ----------------------------
DROP TABLE IF EXISTS `table_collect`;
CREATE TABLE `table_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `read_id` int(11) DEFAULT NULL,
  `usesr_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_collect
-- ----------------------------

-- ----------------------------
-- Table structure for `table_comment`
-- ----------------------------
DROP TABLE IF EXISTS `table_comment`;
CREATE TABLE `table_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `read_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `comment_content` varchar(255) DEFAULT NULL,
  `comment_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `table_give`
-- ----------------------------
DROP TABLE IF EXISTS `table_give`;
CREATE TABLE `table_give` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `read_id` int(11) DEFAULT NULL,
  `laugh_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_give
-- ----------------------------

-- ----------------------------
-- Table structure for `table_laugh`
-- ----------------------------
DROP TABLE IF EXISTS `table_laugh`;
CREATE TABLE `table_laugh` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `laugh_id` int(11) DEFAULT NULL,
  `laugh_content` varchar(255) DEFAULT NULL,
  `laugh_zan` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_laugh
-- ----------------------------

-- ----------------------------
-- Table structure for `table_read`
-- ----------------------------
DROP TABLE IF EXISTS `table_read`;
CREATE TABLE `table_read` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `read_id` int(11) DEFAULT NULL,
  `read_writer` varchar(20) DEFAULT NULL,
  `read_content` varchar(255) DEFAULT NULL,
  `read_image` varchar(255) DEFAULT NULL,
  `read_zan` int(11) DEFAULT NULL,
  `read_commentNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_read
-- ----------------------------

-- ----------------------------
-- Table structure for `table_user`
-- ----------------------------
DROP TABLE IF EXISTS `table_user`;
CREATE TABLE `table_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_phone` varchar(20) DEFAULT NULL,
  `user_password` varchar(20) DEFAULT NULL,
  `user_nickname` varchar(20) DEFAULT NULL,
  `user_sex` varchar(10) DEFAULT NULL,
  `user_image` varchar(100) DEFAULT NULL,
  `user_register_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_user
-- ----------------------------
