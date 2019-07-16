-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: scheduler
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.10.1

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_p4nkloj9932pvpq48q9haruja` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'2019-07-16 19:47:20','2019-07-16 19:47:20','23347 Arnold Cliffs Suite 429','christopher16@simon-patterson.com',NULL,NULL,'Perez-Clayton','(325)131-1771'),(2,'2019-07-16 19:47:20','2019-07-16 19:47:20','5765 Jones Groves',NULL,NULL,NULL,'Hays-Rodriguez',NULL),(3,'2019-07-16 19:47:21','2019-07-16 19:47:21','635 Lisa Tunnel Suite 756','anthony34@hotmail.com',NULL,NULL,'Anderson-Joseph','131.997.8806'),(4,'2019-07-16 19:47:21','2019-07-16 19:47:21','905 Collins Plaza Apt. 488',NULL,NULL,NULL,'Foster, Martinez and Smith',NULL),(5,'2019-07-16 19:47:21','2019-07-16 19:47:21','61719 Mitchell Rue','steve37@yahoo.com',NULL,NULL,'Johnson, Mcmahon and Rhodes','647-871-2340x00836'),(6,'2019-07-16 19:47:21','2019-07-16 19:47:21','7771 Johnson Shore',NULL,NULL,NULL,'Reed, Reynolds and Oconnor',NULL);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `is_active` bit(1) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `person_type` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fwmwi44u55bo4rvwsv0cln012` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (7,'2019-07-16 19:47:20','2019-07-16 19:47:20',_binary '',NULL,'joe@user.com','joe','Imauser','$2a$10$SH3Hh.cUUDcqROJEJWZbDOpptYuS86LcO77nWYbuOXfa3SxLhdFCm',1,NULL),(8,'2019-07-16 19:47:20','2019-07-16 19:47:21',_binary '','406 Johnson Via Suite 075','nicholasrichardson@gmail.com','Kim','Nelson',NULL,3,'641-146-3230'),(9,'2019-07-16 19:47:21','2019-07-16 19:47:21',_binary '','6885 Steven Roads','kevin14@bell.com','David','Lopez',NULL,3,'(611)858-5727x5047'),(10,'2019-07-16 19:47:21','2019-07-16 19:47:21',_binary '','88273 Sandra Land','justin48@garcia.com','Jennifer','Matthews',NULL,3,'01820578563'),(11,'2019-07-16 19:49:49','2019-07-16 19:49:49',_binary '',NULL,'joe@blow.com','Edward','OD','1234567',3,'1234567'),(19,'2019-07-16 19:59:04','2019-07-16 19:59:04',_binary '',NULL,'joe2@blow2.com','Edward2','OD2','1234567',3,'1234567');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `completed` bit(1) NOT NULL,
  `end` datetime NOT NULL,
  `location_id` int(11) NOT NULL,
  `pay_rate` decimal(19,2) DEFAULT NULL,
  `person_id` int(11) NOT NULL,
  `start` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'2019-07-16 19:47:20','2019-07-16 19:47:20',_binary '','2019-07-07 20:06:03',1,19.00,8,'2019-07-07 19:06:03'),(2,'2019-07-16 19:47:21','2019-07-16 19:47:21',_binary '','2019-11-12 22:04:58',3,17.00,8,'2019-11-12 21:04:58'),(3,'2019-07-16 19:47:21','2019-07-16 19:47:21',_binary '','2019-05-05 02:25:38',3,34.00,8,'2019-05-05 01:25:38'),(4,'2019-07-16 19:47:21','2019-07-16 19:47:21',_binary '','2019-08-16 23:39:37',2,28.00,10,'2019-08-16 22:39:37'),(5,'2019-07-16 19:47:21','2019-07-16 19:47:21',_binary '','2019-10-12 06:18:06',2,27.00,10,'2019-10-12 05:18:06');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-16 13:51:26
