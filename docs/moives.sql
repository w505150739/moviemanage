/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : moives

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-04-12 02:42:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_attachs
-- ----------------------------
DROP TABLE IF EXISTS `t_b_attachs`;
CREATE TABLE `t_b_attachs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `type` tinyint(2) DEFAULT NULL COMMENT '文件类型',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态 1、可用 2、已删除',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `suffix` varchar(5000) DEFAULT NULL COMMENT '文件后缀',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `relation_id` bigint(20) DEFAULT NULL COMMENT '关联数据id',
  `relation_type` tinyint(2) DEFAULT NULL COMMENT '关联类型 1、新闻资讯 2、项目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='附件记录表';

-- ----------------------------
-- Records of t_b_attachs
-- ----------------------------

-- ----------------------------
-- Table structure for t_b_member
-- ----------------------------
DROP TABLE IF EXISTS `t_b_member`;
CREATE TABLE `t_b_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '密码盐值',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `invite_code` varchar(20) DEFAULT NULL COMMENT '邀请码',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别 1 男 0 女',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `address` varchar(500) DEFAULT NULL COMMENT '通讯地址',
  `photo` varchar(50) DEFAULT NULL COMMENT '头像存储uuid',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态 1、可用 2、已删除',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
-- Records of t_b_member
-- ----------------------------
INSERT INTO `t_b_member` VALUES ('3', '18818524559', 'd343f07e46d11f2d9cc9a3b430a60be61c0e48b91b512e250a026e7007f74a6e', 'bjJ9MgaiO9UyQOPb356a', '2018-03-24 21:07:05', null, null, '1', null, null, null, '741258@qq.com', 'liuyuzhu', '1', null);

-- ----------------------------
-- Table structure for t_b_news
-- ----------------------------
DROP TABLE IF EXISTS `t_b_news`;
CREATE TABLE `t_b_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '资讯标题',
  `type` tinyint(2) DEFAULT NULL COMMENT '资讯类型 1 广告 2 新闻',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态 1、可用 2、已删除',
  `show_flag` tinyint(2) DEFAULT NULL COMMENT '是否显示 1 显示 0 不显示',
  `content` varchar(5000) DEFAULT NULL COMMENT '资讯内容',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='新闻资讯表';

-- ----------------------------
-- Records of t_b_news
-- ----------------------------
INSERT INTO `t_b_news` VALUES ('1', '扥嗲发动', '2', '1', '1', '<img src=\"http://localhost:8080/moviemanage/viewImage?uuid=b7f0a7e9ff584b7881fe99a347178f18\" alt=\"6712.png\" />赛菲尔正很刚', '2018-04-01 01:37:49', '2018-04-01 01:37:50');
INSERT INTO `t_b_news` VALUES ('2', '测试标题', '1', '1', '1', '<img src=\"http://localhost:8080/moviemanage/viewImage?uuid=1e8697c88046461c8436fa4a97e86453\" alt=\"6712.png\" />as找矿搜寻的，苗冰嗲发动送扥额外', '2018-04-01 11:18:51', '2018-04-01 11:18:52');
INSERT INTO `t_b_news` VALUES ('7', '小翠害人', '1', '2', '0', '<p>新东方东方鱼骨图月<img src=\"http://localhost:8080/moviemanage/viewImage?uuid=edc8b3bf15934377ab0a7f9cf1939900\" alt=\"6712.png\"/>岑等分点等额酸味儿撒safdsadasasdsad</p><ul class=\" list-paddingleft-2\" style=\"list-style-type: disc;\"><li><p>塞阀赛奥法</p></li><li><p>赛奥法打死</p></li><li><p>啊赛佛dsa东安f奥赛f</p></li><li><p>啊赛佛撒扥</p></li><li><p><br/></p></li></ul>', '2018-04-01 16:13:28', '2018-04-02 01:10:49');
INSERT INTO `t_b_news` VALUES ('9', 're团圆屯', '1', '1', '0', '塞阀赛风赛风分仓费当个人扥h<img src=\"http://localhost:8080/moviemanage/viewImage?uuid=8266229e5a1c4dff98df87f035fc73d1\" alt=\"6712.png\" />仓泵费等', '2018-04-01 16:29:13', '2018-04-01 16:29:14');
INSERT INTO `t_b_news` VALUES ('10', '东few费等软腭', '1', '1', '0', '扥僧人的阿塞阀东方<strong>阿塞阀塞阀三</strong><strong><img src=\"http://localhost:8080/moviemanage/viewImage?uuid=75c32d692a184cbd9829feb41a07f273\" alt=\"6712.png\" /></strong><strong>赛风德森</strong>', '2018-04-01 17:16:27', '2018-04-01 17:16:28');
INSERT INTO `t_b_news` VALUES ('11', '测试修改能', '1', '1', '0', '撒扥东啊发<strong>撒扥东啊发</strong><strong>赛奥法sad东安抚</strong><strong>sdaf东安抚</strong><strong>asdf&amp;nbsp;</strong><strong><img src=\"http://localhost:8080/moviemanage/viewImage?uuid=7009b35807274b0b8db178833144d171\" alt=\"6712.png\" /></strong><strong>狂啊赛佛领囧</strong>', '2018-04-01 17:28:04', '2018-04-01 17:28:06');
INSERT INTO `t_b_news` VALUES ('12', '测试修改成功否', '1', '1', '0', '<p>&nbsp; &nbsp;萨芬东非<strong>赛发动发</strong><strong>阿赛发动安抚</strong><strong>东安抚东安抚</strong><strong>洗澡负债森</strong><strong>撒扥东非</strong><strong>在线崔满您看好</strong><strong>，口橡胶岑拽进动画</strong><strong>sad灵泛灵动案件&amp;nbsp;</strong>\n\n\n &nbsp; &nbsp;<strong>塞阀撒扥</strong> &amp;nbsp;<strong>我的红色字体</strong>\n\n\n &nbsp; &nbsp;\n &nbsp; &nbsp; &nbsp; &nbsp;\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<strong>上一上车</strong>\n &nbsp; &nbsp; &nbsp; &nbsp;\n &nbsp; &nbsp;\n &nbsp; &nbsp;\n &nbsp; &nbsp; &nbsp; &nbsp;\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<strong>5端月肉盾</strong>\n &nbsp; &nbsp; &nbsp; &nbsp;\n &nbsp; &nbsp;\n &nbsp; &nbsp;\n &nbsp; &nbsp; &nbsp; &nbsp;\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<strong>刚均衡</strong>\n &nbsp; &nbsp;</p><p><span style=\"color: rgb(255, 0, 0);\">扥根等</span></p><p>扥个放灯<span style=\"color: rgb(255, 0, 0);\">扥个df放灯fd</span></p><p>dfs该放松g&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p><p><img src=\"http://localhost:8080/moviemanage/viewImage?uuid=21e848e979074a97aa9396362ea48932\" title=\"test\" alt=\"test.jpg\"/></p>', '2018-04-01 17:35:07', '2018-04-02 01:06:39');
INSERT INTO `t_b_news` VALUES ('13', '测试销毁', '2', '1', '1', '思想扥东方', '2018-04-01 17:40:21', '2018-04-01 17:40:22');
INSERT INTO `t_b_news` VALUES ('14', '测试销毁', '2', '1', '0', '<p>撒扥领撒扥领翻看领</p><p><img src=\"http://localhost:8080/moviemanage/viewImage?uuid=057d06ef5c364407a4d334bda880c4c7\" title=\"6712\" alt=\"6712.png\"/></p>', '2018-04-01 17:42:08', '2018-04-06 14:19:44');
INSERT INTO `t_b_news` VALUES ('18', '赛粉丝', '1', '1', '0', '<p>二团热的天&nbsp;</p><p>贵南鬼仓就跟</p><ol class=\" list-paddingleft-2\" style=\"list-style-type: decimal;\"><li><p>小催下扥个</p></li><li><p>方耿芳</p></li></ol><p><img src=\"http://localhost:8080/moviemanage/viewImage?uuid=d55eb0d6eccf4b4783473619d54980c2\" title=\"6712\" alt=\"6712.png\"/></p>', '2018-04-06 14:37:24', '2018-04-06 14:41:17');
INSERT INTO `t_b_news` VALUES ('19', '测试图', '2', '1', '1', null, '2018-04-12 01:35:07', null);
INSERT INTO `t_b_news` VALUES ('20', '测试图2', '1', '1', '1', null, '2018-04-12 01:36:02', null);
INSERT INTO `t_b_news` VALUES ('21', '测试图3', '1', '1', '1', null, '2018-04-12 01:37:43', null);
INSERT INTO `t_b_news` VALUES ('22', '撒扥测试', '1', '1', '0', null, '2018-04-12 01:41:22', null);
INSERT INTO `t_b_news` VALUES ('23', 'sad发动', '1', '1', '1', null, '2018-04-12 01:46:40', null);
INSERT INTO `t_b_news` VALUES ('24', '赛发动', '2', '1', '1', null, '2018-04-12 01:48:07', null);
INSERT INTO `t_b_news` VALUES ('25', '撒扥', '1', '1', '1', null, '2018-04-12 02:28:12', null);

-- ----------------------------
-- Table structure for t_b_project
-- ----------------------------
DROP TABLE IF EXISTS `t_b_project`;
CREATE TABLE `t_b_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(200) DEFAULT NULL COMMENT '项目名称',
  `movie_id` bigint(20) DEFAULT NULL COMMENT '影片id',
  `project_cost` decimal(10,2) DEFAULT NULL COMMENT '项目成本',
  `project_header` varchar(255) DEFAULT NULL COMMENT '项目发起人',
  `financing_money` decimal(10,2) DEFAULT NULL COMMENT '筹资金额',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '投资开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '投资结束时间',
  `project_label` varchar(255) DEFAULT NULL COMMENT '项目标签 如 永久版权 永久福利等',
  `project_cover_charge` decimal(10,2) DEFAULT NULL COMMENT '项目服务费',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `project_type` tinyint(2) DEFAULT NULL COMMENT '项目类型 1 网络电影 2 院线电影',
  `project_status` tinyint(2) DEFAULT NULL COMMENT '项目状态 1 即将上线 2 正在募集 3 募集完成',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态 1、可用 2、已删除',
  `examine_status` tinyint(2) DEFAULT NULL COMMENT '审核状态 1、新建 2、已提交未审核 3 驳回 4 审核通过',
  `publish_status` tinyint(2) DEFAULT NULL COMMENT '是否发布 1 发布 0 未发布',
  `project_des` text COMMENT '项目介绍',
  `remark` varchar(500) DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='项目表';

-- ----------------------------
-- Records of t_b_project
-- ----------------------------
INSERT INTO `t_b_project` VALUES ('1', '测试项目', null, '1200.00', '自己', '1470.00', null, null, '永久福利', '5000.00', null, '1', '1', null, '1', null, '1', null, null);
INSERT INTO `t_b_project` VALUES ('2', '测试项目2', null, '6577.00', '我', '15985.00', null, null, '永久版权', '5000.00', '2018-04-04 00:23:52', '2', '2', null, '1', null, '0', null, null);
INSERT INTO `t_b_project` VALUES ('3', '测试项目3', null, '11111.00', '蓝', '12122112.00', null, null, '哈哈哈', '158.00', '2018-04-04 00:27:07', '1', '3', null, '1', null, '1', null, null);
INSERT INTO `t_b_project` VALUES ('4', '测试项目4', null, '58421.00', '哈哈', '14521.00', '2018-04-04 00:00:00', '2018-04-19 00:00:00', '阿龙定积分', '159852.00', '2018-04-04 00:36:15', '2', '2', '2018-04-07 01:49:54', '1', '3', '0', '<p><img src=\"http://193.112.67.154:8080/viewImage?uuid=fe18bec64ca54c019f7cb21ac9d118c0\" title=\"6712\" alt=\"6712.png\"/></p><p>东发动安抚</p><p>扥改凤</p>', '东阿');
INSERT INTO `t_b_project` VALUES ('7', 'xcvsd', null, '322.00', '32', '111.00', '2018-04-06 01:58:11', '2018-04-27 00:00:00', 'wqeqw', '222.00', '2018-04-06 01:58:56', '2', '1', null, '1', '1', '1', null, null);
INSERT INTO `t_b_project` VALUES ('8', 'xfdgdsg', null, '33.00', '22', '333.00', '2018-04-06 01:59:47', '2018-04-19 00:00:00', 'werewr', '333.00', '2018-04-06 02:00:18', '1', '1', null, '1', '1', '1', null, null);
INSERT INTO `t_b_project` VALUES ('9', '112', null, '111.00', 'qqq', '22.00', '2018-04-06 02:02:17', '2018-04-27 00:00:00', 'qweqwe', '111.00', '2018-04-06 02:02:42', '2', '1', null, '1', '1', '1', null, null);
INSERT INTO `t_b_project` VALUES ('10', '212', null, '333.00', 'wqwq', '212.00', '2018-04-06 02:03:45', '2018-04-27 00:00:00', 'wqewq', '211.00', '2018-04-06 02:04:13', '2', '1', null, '1', '1', '1', null, null);
INSERT INTO `t_b_project` VALUES ('11', '好灵儿吧', null, '21.00', 'we赛发动', '321.00', '2018-04-06 02:07:07', '2018-04-20 00:00:00', 'ewr3东风东方', '3214.00', '2018-04-06 02:07:53', '1', '3', '2018-04-06 04:41:11', '1', '3', '1', '<p>零三扥哦</p><p>塞饭龙儿按揭房</p><ul class=\" list-paddingleft-2\" style=\"list-style-type: disc;\"><li><p>赛发动范</p></li><li><p>赛力帆零件</p></li><li><p>东翻领赛按揭房</p></li><li><p>塞阀看似来</p><p><img src=\"http://localhost:8080/moviemanage/viewImage?uuid=a78afc65920b4642a65fa1ecbddd0503\" title=\"6712\" alt=\"6712.png\"/></p></li><li><p>散开很看桑德<br/></p></li></ul>', 'zdfsaf');
INSERT INTO `t_b_project` VALUES ('12', '增加了', null, '1221.00', '小扥', '322.00', '2018-04-06 02:11:35', '2018-04-18 00:00:00', '法等扥个', '456.00', '2018-04-06 02:12:21', '2', '1', '2018-04-06 04:23:20', '1', '4', '1', '<p>塞阀撒扥</p><ul class=\" list-paddingleft-2\" style=\"list-style-type: disc;\"><li><p>向翠玲</p></li><li><p>赛佛吃完</p></li><li><p>赛翻领赛季</p></li><li><p>塞饭欧成</p></li></ul><p><img src=\"http://localhost:8080/moviemanage/viewImage?uuid=64ca6a670d1241ce9c06a8eb21889c94\" title=\"6712\" alt=\"6712.png\"/></p><p>塞阀框架</p><p><span style=\"color: rgb(255, 0, 0);\">想到发展岭东路</span><br/></p><p><span style=\"color: rgb(255, 0, 0);\">性扥拽囧当</span></p><p><span style=\"color: rgb(255, 0, 0);\">小翠娘行测女</span></p>', 'sdfasdf');

-- ----------------------------
-- Table structure for t_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_config`;
CREATE TABLE `t_sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of t_sys_config
-- ----------------------------
INSERT INTO `t_sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态 1、可用 2、已删除',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '0', '1');
INSERT INTO `t_sys_menu` VALUES ('2', '1', '用户列表', 'modules/sys/user.html', null, '1', 'fa fa-user', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('3', '1', '角色管理', 'modules/sys/role.html', null, '1', 'fa fa-user-secret', '2', '1');
INSERT INTO `t_sys_menu` VALUES ('4', '1', '菜单管理', 'modules/sys/menu.html', null, '1', 'fa fa-th-list', '3', '2');
INSERT INTO `t_sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0', '2');
INSERT INTO `t_sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0', '2');
INSERT INTO `t_sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0', '2');
INSERT INTO `t_sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0', '2');
INSERT INTO `t_sys_menu` VALUES ('29', '1', '系统日志', 'modules/sys/log.html', 'sys:log:list', '1', 'fa fa-file-text-o', '7', '2');
INSERT INTO `t_sys_menu` VALUES ('30', '1', '文件上传', 'modules/oss/oss.html', 'sys:oss:all', '1', 'fa fa-file-image-o', '6', '2');
INSERT INTO `t_sys_menu` VALUES ('31', '0', '项目管理', '', null, '0', 'fa fa-film', '3', '1');
INSERT INTO `t_sys_menu` VALUES ('40', '0', '会员管理', '', null, '0', 'fa fa-user', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('41', '40', '会员列表', 'modules/member/member.html', null, '1', 'fa fa-user', '6', '1');
INSERT INTO `t_sys_menu` VALUES ('42', '41', '查看', null, 'sys:member:list,sys:member:info', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('43', '41', '新增', null, 'sys:member:save', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('44', '41', '修改', null, 'sys:member:update', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('45', '41', '删除', null, 'sys:member:delete', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('46', '0', '资讯管理', null, null, '0', 'fa fa-fw fa-newspaper-o', '2', '1');
INSERT INTO `t_sys_menu` VALUES ('47', '46', '新闻资讯', 'modules/news/news.html', null, '1', 'fa  fa-fw fa-newspaper-o', '6', '1');
INSERT INTO `t_sys_menu` VALUES ('48', '47', '查看', null, 'sys:news:list,sys:news:info', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('49', '47', '新增', null, 'sys:news:save', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('50', '47', '修改', null, 'sys:news:update', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('51', '47', '删除', null, 'sys:news:delete', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('52', '31', '项目管理', 'modules/project/project.html', null, '1', 'fa fa-film', '6', '1');
INSERT INTO `t_sys_menu` VALUES ('53', '52', '查看', null, 'project:project:list,project:project:info', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('54', '52', '新增', null, 'project:project:save', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('55', '52', '修改', null, 'project:project:update', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('56', '52', '删除', null, 'project:project:delete', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('57', '52', '提审', null, 'project:project:approval', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('59', '52', '发布', null, 'project:project:publish', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('60', '31', '项目审核', 'modules/project/approvalpro.html', null, '1', null, '2', '1');
INSERT INTO `t_sys_menu` VALUES ('61', '60', '审核', null, 'project:project:examine,project:project:approvallist', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('62', '1', '附件记录', 'modules/project/attachs.html', null, '1', 'fa fa-file-code-o', '6', '1');
INSERT INTO `t_sys_menu` VALUES ('63', '62', '查看', null, 'project:attachs:list,project:attachs:info', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('64', '62', '新增', null, 'project:attachs:save', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('65', '62', '修改', null, 'project:attachs:update', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('66', '62', '删除', null, 'project:attachs:delete', '2', null, '6', '1');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', '项目审核员', '审核项目专有角色', '1', '2018-04-03 00:26:20');
INSERT INTO `t_sys_role` VALUES ('2', '管理员', '管理员', '1', '2018-04-03 00:30:28');

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------
INSERT INTO `t_sys_role_menu` VALUES ('5', '2', '1');
INSERT INTO `t_sys_role_menu` VALUES ('6', '2', '2');
INSERT INTO `t_sys_role_menu` VALUES ('7', '2', '15');
INSERT INTO `t_sys_role_menu` VALUES ('8', '2', '16');
INSERT INTO `t_sys_role_menu` VALUES ('9', '2', '17');
INSERT INTO `t_sys_role_menu` VALUES ('10', '2', '18');
INSERT INTO `t_sys_role_menu` VALUES ('11', '2', '3');
INSERT INTO `t_sys_role_menu` VALUES ('12', '2', '19');
INSERT INTO `t_sys_role_menu` VALUES ('13', '2', '20');
INSERT INTO `t_sys_role_menu` VALUES ('14', '2', '21');
INSERT INTO `t_sys_role_menu` VALUES ('15', '2', '22');
INSERT INTO `t_sys_role_menu` VALUES ('16', '2', '31');
INSERT INTO `t_sys_role_menu` VALUES ('17', '2', '52');
INSERT INTO `t_sys_role_menu` VALUES ('18', '2', '53');
INSERT INTO `t_sys_role_menu` VALUES ('19', '2', '54');
INSERT INTO `t_sys_role_menu` VALUES ('20', '2', '55');
INSERT INTO `t_sys_role_menu` VALUES ('21', '2', '56');
INSERT INTO `t_sys_role_menu` VALUES ('22', '2', '57');
INSERT INTO `t_sys_role_menu` VALUES ('23', '2', '58');
INSERT INTO `t_sys_role_menu` VALUES ('24', '2', '59');
INSERT INTO `t_sys_role_menu` VALUES ('25', '2', '40');
INSERT INTO `t_sys_role_menu` VALUES ('26', '2', '41');
INSERT INTO `t_sys_role_menu` VALUES ('27', '2', '42');
INSERT INTO `t_sys_role_menu` VALUES ('28', '2', '43');
INSERT INTO `t_sys_role_menu` VALUES ('29', '2', '44');
INSERT INTO `t_sys_role_menu` VALUES ('30', '2', '45');
INSERT INTO `t_sys_role_menu` VALUES ('31', '2', '46');
INSERT INTO `t_sys_role_menu` VALUES ('32', '2', '47');
INSERT INTO `t_sys_role_menu` VALUES ('33', '2', '48');
INSERT INTO `t_sys_role_menu` VALUES ('34', '2', '49');
INSERT INTO `t_sys_role_menu` VALUES ('35', '2', '50');
INSERT INTO `t_sys_role_menu` VALUES ('36', '2', '51');
INSERT INTO `t_sys_role_menu` VALUES ('37', '1', '31');
INSERT INTO `t_sys_role_menu` VALUES ('38', '1', '60');
INSERT INTO `t_sys_role_menu` VALUES ('39', '1', '61');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11');
INSERT INTO `t_sys_user` VALUES ('2', 'liuyuzhu', 'b5f936619f46ac44a63e82e14c8911e6d150e1e9b218a774cd2134b6a4a14980', 'MWjcJMWL5tUmQFOGaquY', 'liuyuzhu@126.com', '15888888888', '1', '1', '2018-04-03 00:26:43');
INSERT INTO `t_sys_user` VALUES ('3', 'movie', '31fc99389a9725abbba22cb0cc4ccd23027a33227bbca2ad33538c69215fa4fd', 'dwxeZIvgBATW9x52DFrW', 'movie@126.com', '18818544574', '1', '1', '2018-04-03 00:29:47');

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('1', '2', '1');
INSERT INTO `t_sys_user_role` VALUES ('2', '3', '2');

-- ----------------------------
-- Table structure for t_sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_token`;
CREATE TABLE `t_sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of t_sys_user_token
-- ----------------------------
INSERT INTO `t_sys_user_token` VALUES ('1', '945947bd0cfa04b03ab1a30540309918', '2018-04-12 02:34:52', '2018-04-12 01:34:52');
INSERT INTO `t_sys_user_token` VALUES ('2', '027dcd44cc1500d7d3b9fc0b225a3aee', '2018-04-06 16:36:31', '2018-04-06 15:36:31');
INSERT INTO `t_sys_user_token` VALUES ('3', 'b0ab0f033e4248cf20cc8302c75a611a', '2018-04-03 01:00:48', '2018-04-03 00:30:48');
