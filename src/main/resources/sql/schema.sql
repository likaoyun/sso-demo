/*DROP TABLE IF EXISTS `user`;*/
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `userDesc` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `accountType` int(5) DEFAULT NULL,
   `lockStatus` int(5) DEFAULT NULL,
   `personCode` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `personName` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `createTime` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `modifyTime` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
