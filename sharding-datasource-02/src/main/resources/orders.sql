-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
                        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
                        `user_id` int(16) DEFAULT NULL COMMENT '用户编号',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';