-- MySQL dump 10.16  Distrib 10.1.41-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: 192.168.1.12    Database: scheduler
-- ------------------------------------------------------
-- Server version	5.7.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `latitude` decimal(10,8) DEFAULT NULL,
  `longitude` decimal(11,8) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (20,'2019-09-17 06:13:15','2019-09-17 13:14:10','Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12240, Indonesia','lantera@school.com',-6.22205450,106.80723010,'Lentera School','555-1212'),(21,'2019-09-17 06:13:15','2019-09-17 13:14:17','Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12730, Indonesia','aussie@school.com',-6.22205450,106.80723010,'Australian Independent','367-1212'),(22,'2019-09-17 06:13:15','2019-09-17 13:14:23','Kota Jakarta Barat, Daerah Khusus Ibukota Jakarta 11630, Indonesia','national.high@school.com',-6.22205680,106.77221000,'National High Jakarta','444-1212'),(23,'2019-09-17 06:13:15','2019-09-17 13:14:29','Kota Jkt Utara, Daerah Khusus Ibukota Jakarta 14250, Indonesia','beacon.academy@school.com',-6.22205680,106.77221000,'Beacon Academy','555-1212'),(24,'2019-09-17 06:13:15','2019-09-17 13:14:41','Kota Jkt Utara, Daerah Khusus Ibukota Jakarta 14460, Indonesia','singapore.pantai@school.com',-6.22205680,106.77221000,'Singapore School Pantai','555-7676'),(25,'2019-09-17 06:13:15','2019-09-17 13:15:55','Washington St Mission Hills San Diego CA','grant@school.com',32.74961600,-117.17853600,'Grant Elementary','215-323-2734'),(26,'2019-09-17 06:13:15','2019-09-17 13:16:05','1st Ave Hillcrest San Diego CA','florence@school.com',32.74900090,-117.16636730,'Florence Elementary','444-1288');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-17  6:16:53
