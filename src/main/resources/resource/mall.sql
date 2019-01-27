-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: mall
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `buy_goods`
--

DROP TABLE IF EXISTS `buy_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buy_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `buyPrice` float NOT NULL,
  `time` varchar(100) DEFAULT NULL,
  `uid` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy_goods`
--

LOCK TABLES `buy_goods` WRITE;
/*!40000 ALTER TABLE `buy_goods` DISABLE KEYS */;
INSERT INTO `buy_goods` VALUES (11,7,5,777,'2019-01-25 17:21:35',1),(12,1,3,10,'2019-01-25 17:22:29',1),(13,9,1,333333,'2019-01-25 21:37:51',1),(14,9,3,333333,'2019-01-25 22:05:50',1),(15,12,2,151515,'2019-01-25 22:05:50',1),(16,11,2,12121200,'2019-01-25 22:05:50',1),(17,9,1,333333,'2019-01-25 22:29:36',1),(18,7,2,888,'2019-01-26 11:53:49',1),(19,9,3,333333,'2019-01-26 11:53:49',1),(24,11,1,12121200,'2019-01-27 10:13:20',1),(25,12,1,151515,'2019-01-27 10:13:21',1),(26,7,2,999,'2019-01-27 10:13:21',1),(27,7,12,999,'2019-01-27 13:50:01',1),(28,11,8,12121200,'2019-01-27 13:50:01',1),(29,17,6,2,'2019-01-27 13:53:49',1),(30,7,11,999,'2019-01-27 13:54:33',1),(31,7,1,999,'2019-01-27 13:55:23',1),(32,7,10,777888,'2019-01-27 17:29:58',1),(33,17,28,2,'2019-01-27 17:29:58',1),(34,7,10,777888,'2019-01-27 21:49:59',1),(35,17,28,2,'2019-01-27 21:49:59',1),(36,19,10,122,'2019-01-27 21:49:59',1),(37,26,9,88888,'2019-01-27 23:50:29',1);
/*!40000 ALTER TABLE `buy_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(80) NOT NULL,
  `sellPrice` float NOT NULL,
  `buyPrice` float NOT NULL,
  `summary` varchar(140) DEFAULT NULL,
  `detail` varchar(1000) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `buyTime` varchar(100) DEFAULT NULL,
  `time` varchar(100) DEFAULT NULL,
  `totalCount` int(11) DEFAULT '0',
  `sellCount` int(11) DEFAULT '0',
  `sell` tinyint(1) DEFAULT NULL,
  `buyCount` int(11) DEFAULT '0',
  `buy` tinyint(1) DEFAULT NULL,
  `sellerId` int(11) NOT NULL DEFAULT '2',
  PRIMARY KEY (`gid`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,'测试1',10,0,'测试1','测试1','https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=488104485,2100192060&fm=26&gp=0.jpg','2019-01-23 15:21:02','2019-01-23 15:21:02',0,9,1,0,1,2),(7,'777888',100000000000,0,'777','777','/image/1548602714530暴风截图20161264834829.jpg','2019-01-25 08:41:19','2019-01-25 08:41:19',0,53,1,0,1,2),(9,'333333333333',333333,0,'33333333333','33333333333','/image/1548377253827暴风截图20161264372193.jpg','2019-01-25 08:47:43','2019-01-25 08:47:43',0,8,1,0,1,2),(11,'121212121212',12121200,0,'121212121212','1212121212','/image/1548400104192暴风截图20161217178355927.jpg','2019-01-25 15:08:36','2019-01-25 15:08:36',0,11,1,0,1,2),(12,'151515',151515,0,'151515','151515','/image/1548408514505暴风截图20161264251292.jpg','2019-01-25 17:28:44','2019-01-25 17:28:44',0,3,1,0,1,2),(17,'二二二',2,0,'二二二','二二二','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548565753459&di=73914a5b90487b7d48a4f9be6c0ca192&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F00%2F22%2F20200000013920144723220120010.jpg','2019-01-27 10:22:15','2019-01-27 10:22:15',0,62,1,0,1,2),(19,'测试222',122,0,'测试222','测试222','/image/1548581788261暴风截图20161212116662929.jpg','2019-01-27 17:36:36','2019-01-27 17:36:36',0,10,1,0,1,2),(26,'88888888',88888,0,'8888888888884','888888888888','/image/1548602547123暴风截图20161264536431.jpg','2019-01-27 23:22:32','2019-01-27 23:22:32',0,9,1,0,1,2),(27,'999999',10000000,0,'999999999','9999999999999','/image/1548602742246暴风截图20161265125756.jpg','2019-01-27 23:25:47','2019-01-27 23:25:47',0,0,0,0,0,2);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_password` varchar(32) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'buyer','37254660e226ea65ce6f1efd54233424'),(2,'seller','981c57a5cfb0f868e064904b8745766f');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-28  0:11:17
