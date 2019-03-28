/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.17-log : Database - db_brush
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_brush` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `db_brush`;

/*Table structure for table `t_certification` */

DROP TABLE IF EXISTS `t_certification`;

CREATE TABLE `t_certification` (
  `



real_name_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '实名认证编号',
  `real_name` varchar(20) NOT NULL COMMENT '真实姓名',
  `id_number` varchar(255) NOT NULL COMMENT '身份证号',
  `img_front` varchar(255) NOT NULL COMMENT '身份证正面照',
  `img_back` varchar(255) NOT NULL COMMENT '身份证背面照',
  PRIMARY KEY (`



real_name_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_certification` */

/*Table structure for table `t_payment_method` */

DROP TABLE IF EXISTS `t_payment_method`;

CREATE TABLE `t_payment_method` (
  `payment_method_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '允许支付方式编号',
  `payment_method_name` varchar(255) NOT NULL COMMENT '允许支付方式',
  PRIMARY KEY (`payment_method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_payment_method` */

insert  into `t_payment_method`(`payment_method_id`,`payment_method_name`) values (1,'信用卡'),(2,'花呗'),(3,'集分宝'),(4,'淘金币'),(5,'天猫积分');

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Data for the table `t_role` */

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

/*Data for the table `t_role_menu` */

/*Table structure for table `t_shop` */

DROP TABLE IF EXISTS `t_shop`;

CREATE TABLE `t_shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺编号',
  `platform_id` int(11) NOT NULL COMMENT '平台编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `shop_name` varchar(255) NOT NULL COMMENT '店铺名称',
  `qq` varchar(50) DEFAULT NULL COMMENT 'QQ号码',
  `telphone` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `link_address` varchar(255) DEFAULT NULL COMMENT '商品链接地址',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_shop` */

/*Table structure for table `t_task` */

DROP TABLE IF EXISTS `t_task`;

CREATE TABLE `t_task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务编号',
  `task_status` int(11) NOT NULL COMMENT '任务状态 0-待审批，1-进行中，2-通过，3-已完成，4-已失效',
  `existence_state` int(11) DEFAULT NULL COMMENT '存在状态 0-关闭，1-重置，2-删除',
  `task_title` varchar(255) NOT NULL COMMENT '任务标题',
  `platform_id` int(11) NOT NULL COMMENT '任务平台编号',
  `task_entry` int(1) NOT NULL COMMENT '任务入口 0-关键词，1-淘口令，2-扫描二维码，3-已买到宝贝，4-其他',
  `key_word` varchar(100) DEFAULT NULL COMMENT '进店关键词',
  `search_sort` int(11) DEFAULT NULL COMMENT '搜索排序 0-综合排序，1-按人气，2-按销量',
  `minimum_price` decimal(10,2) DEFAULT NULL COMMENT '价格区间-最低价',
  `highest_price` decimal(10,2) DEFAULT NULL COMMENT '价格区间-最高价',
  `ambush` varchar(255) DEFAULT NULL COMMENT '淘口令',
  `qr_code` varchar(255) DEFAULT NULL COMMENT '二维码图片',
  `other_address` varchar(255) DEFAULT NULL COMMENT '其他入口地址',
  `other_ instructions` varchar(255) DEFAULT NULL COMMENT '其他入口说明',
  `placeofdelivery` varchar(255) DEFAULT NULL COMMENT '发货地',
  `task_link` varchar(255) DEFAULT NULL COMMENT '任务链接',
  `task_interval` int(11) DEFAULT NULL COMMENT '任务间隔 0-所有店铺，1-同一店铺，2-同一链接',
  `interval_days` decimal(10,0) DEFAULT NULL COMMENT '任务间隔天数',
  `coupon_address` varchar(255) DEFAULT NULL COMMENT '优惠券地址',
  `order_price` decimal(10,2) DEFAULT NULL COMMENT '下单价格',
  `order_number` decimal(10,0) DEFAULT NULL COMMENT '下单件数',
  `additional_explanation` varchar(255) DEFAULT NULL COMMENT '补充说明',
  `pay_date` int(11) DEFAULT NULL COMMENT '下单付款时间 0-可立即付款，1-1小时后，2-次日',
  `product_evaluation` int(11) DEFAULT NULL COMMENT '产品评价 0-五星好评，自由发挥；1-指定评论，2-五星评价，无需评语',
  `evaluation` varchar(666) DEFAULT NULL COMMENT '评语',
  `slide_show` int(11) DEFAULT NULL COMMENT '是否晒图 0-无需晒图，1-指定晒图',
  `customer_service` int(11) DEFAULT NULL COMMENT '咨询客服 0-需要咨询客服，1-无需聊天',
  `attention_service` int(11) DEFAULT NULL COMMENT '关注服务 1-关注店铺，2-收藏产品',
  `receipt_time` int(11) DEFAULT NULL COMMENT '签收收货时长 0-签收48小时内，1-签收三日后收货',
  `stay_product_time` int(11) DEFAULT NULL COMMENT '停留进店产品时长 0-停留一分钟，1-停留三分钟，2-停留5分钟',
  `stay_other_product` int(11) DEFAULT NULL COMMENT '停留店内其他产品 0-停留1分钟，1-停留3分钟',
  `order_form` int(11) DEFAULT NULL COMMENT '下单方式 0-加入购物车，1-直接下单',
  `message_reminder` varchar(255) DEFAULT NULL COMMENT '留言提醒',
  `shokey_sex` int(11) DEFAULT NULL COMMENT '试客性别 0-不限 1-男，2-女',
  `shokey_grade` int(11) DEFAULT NULL COMMENT '试客等级 0-不限，1-三星以上，2-钻石以上',
  `shokey_region` int(11) DEFAULT NULL COMMENT '试客区域 0-不限，1-指定地区可接，2-添加禁止地区',
  `release_time` int(11) DEFAULT NULL COMMENT '发布时间 0-立即发布，1-指定时间段',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `time_interval` int(11) DEFAULT NULL COMMENT '时间间隔 0-不重复，1-每天，2-没7天(每周)',
  `publishing_frequency` int(11) DEFAULT NULL COMMENT '发布频率(小时)',
  `consume_gold` decimal(10,2) NOT NULL COMMENT '消耗金币（/单）',
  `append_gold` decimal(10,2) DEFAULT NULL COMMENT '附加金币(/单)',
  `number` decimal(10,2) DEFAULT NULL COMMENT '单数',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_task` */

/*Table structure for table `t_task_number` */

DROP TABLE IF EXISTS `t_task_number`;

CREATE TABLE `t_task_number` (
  `task_number_id` int(11) NOT NULL COMMENT '任务号编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `membership_name` varchar(100) NOT NULL COMMENT '会员名',
  PRIMARY KEY (`task_number_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_task_number` */

/*Table structure for table `t_task_order` */

DROP TABLE IF EXISTS `t_task_order`;

CREATE TABLE `t_task_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务订单编号',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_task_order` */

/*Table structure for table `t_task_payment_method` */

DROP TABLE IF EXISTS `t_task_payment_method`;

CREATE TABLE `t_task_payment_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `task_id` int(11) NOT NULL COMMENT '任务编号',
  `payment_method_id` int(11) NOT NULL COMMENT '允许支付方式编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_task_payment_method` */

/*Table structure for table `t_task_platform` */

DROP TABLE IF EXISTS `t_task_platform`;

CREATE TABLE `t_task_platform` (
  `platform_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务平台编号',
  `platform_name` varchar(255) NOT NULL COMMENT '任务平台名称',
  PRIMARY KEY (`platform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_task_platform` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '登录密码',
  `security_password` varchar(100) DEFAULT NULL COMMENT '安全密码',
  `salt` varchar(20) NOT NULL COMMENT '盐',
  `secret` varchar(100) NOT NULL COMMENT '谷歌验证器',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `qq` varchar(100) DEFAULT NULL COMMENT 'QQ',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态：0-正常,1-禁用',
  `registration_time` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_user` */

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `mobile` varchar(20) DEFAULT NULL COMMENT '用户手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

/*Data for the table `t_user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
