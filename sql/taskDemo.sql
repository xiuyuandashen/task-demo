/*
 Navicat Premium Data Transfer

 Source Server         : docker_mysql1
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : 192.168.150.128:3306
 Source Schema         : taskDemo

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 16/05/2022 14:37:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_task
-- ----------------------------
DROP TABLE IF EXISTS `tb_task`;
CREATE TABLE `tb_task`  (
  `task_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '定时任务id',
  `task_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定时任务名称',
  `task_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定时任务描述',
  `task_exp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定时任务Cron表达式',
  `task_status` int(0) NULL DEFAULT NULL COMMENT '定时任务状态，0停用 1启用',
  `task_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定时任务的Runnable任务类完整路径',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '动态定时任务表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_task
-- ----------------------------
INSERT INTO `tb_task` VALUES ('1', '测试打印helloworld', '这是一个描述', '0/10 * * * * ? ', 0, 'com.example.demo.Task.HelloTask', '2022-05-16 09:59:03', NULL);
INSERT INTO `tb_task` VALUES ('2', '这是一个含参数的task', '这是一个描述', '0/10 * * * * ? ', 0, 'com.example.demo.Task.parameterTask', '2022-05-16 10:41:10', NULL);
INSERT INTO `tb_task` VALUES ('3', '动态生成的class的测试', '动态生成的class的测试', '0/10 * * * * ? ', 0, 'com.example.demo.Task.HelloTask2', '2022-05-16 14:12:10', NULL);

SET FOREIGN_KEY_CHECKS = 1;
