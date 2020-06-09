-- 2020-06-08 14:46:28 by 陈永鹏
CREATE TABLE `good`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`extdata` varchar(100) DEFAULT NULL COMMENT '扩展数据',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_by` bigint(20) NULL DEFAULT 0 COMMENT '创建人',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '商品信息表';
-- 2020-06-08 14:49:55 by 陈永鹏
ALTER TABLE `good` ADD COLUMN `name` varchar(100) COMMENT '商品名称' AFTER `id`;
-- 2020-06-08 14:49:55 by 陈永鹏
ALTER TABLE `good` ADD COLUMN `price` varchar(100) COMMENT '商品价格' AFTER `name`;
-- 2020-06-08 14:49:55 by 陈永鹏
ALTER TABLE `good` MODIFY COLUMN `extdata` varchar(100) COMMENT '扩展数据' AFTER `price`;
