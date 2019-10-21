SET NAMES utf8;

DROP TABLE IF EXISTS `novel`;
CREATE TABLE `novel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `novelname` varchar(50) DEFAULT NULL comment '书名',
  `writer` varchar(255) DEFAULT NULL comment '作者',
  `type` varchar(255) DEFAULT NULL comment '类型',
  PRIMARY KEY (`id`)
);
ALTER TABLE `novel` ADD UNIQUE (`novelname`);
ALTER TABLE `novel` ADD UNIQUE (`writer`);
ALTER TABLE `novel` ADD UNIQUE (`type`);
