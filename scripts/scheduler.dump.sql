-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: scheduler
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.19.04.1

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
-- Table structure for table `employee_school`
--

DROP TABLE IF EXISTS `employee_school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_school` (
  `employee_id` bigint(20) NOT NULL,
  `location_id` bigint(20) NOT NULL,
  PRIMARY KEY (`employee_id`,`location_id`),
  KEY `FKgf9ennkhmhh9mys2tj3116n67` (`location_id`),
  CONSTRAINT `FK5fwjj5wc8y3oxbmgmyvv0y287` FOREIGN KEY (`employee_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKgf9ennkhmhh9mys2tj3116n67` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_school`
--

LOCK TABLES `employee_school` WRITE;
/*!40000 ALTER TABLE `employee_school` DISABLE KEYS */;
INSERT INTO `employee_school` VALUES (6,1),(2,2),(9,2);
/*!40000 ALTER TABLE `employee_school` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'2019-07-23 22:06:08','2019-07-23 22:06:08','001 Mary Bypass','christensenchristopher@jones.info',-8.65660035,-35.40847327,'Macdonald-Brown','+1-708-256-8877x5677'),(2,'2019-07-23 22:06:08','2019-07-23 22:06:08','9529 Mills Mission','dacosta@yahoo.com',6.79687174,-163.85062657,'Ramirez-Lopez','001-366-965-7674x89174'),(3,'2019-07-23 22:07:29','2019-07-23 22:07:29','77229 Bush Lodge Suite 291','erikajensen@gmail.com',79.63517079,-158.11579557,'Marshall-Escobar','3795346283'),(4,'2019-07-23 22:07:29','2019-07-23 22:07:29','1213 Norman Fort Suite 296','barronjames@hotmail.com',55.70983743,-2.04462414,'Schultz, Erickson and Bowers','+1-320-724-7453x805'),(5,'2019-07-23 22:08:20','2019-07-23 22:08:20','718 Rachel Springs','josephlowe@hawkins-howard.com',17.36903368,33.40765736,'Armstrong Ltd','+1-220-388-3652'),(6,'2019-07-23 22:08:20','2019-07-23 22:08:20','25483 Kennedy Courts Suite 968','sharon86@gmail.com',50.79979907,-43.93922048,'Hall LLC','(942)696-4738'),(7,'2019-07-23 22:10:10','2019-07-23 22:10:10','01641 Frey Oval Apt. 265','dawngomez@cox-dyer.info',-69.97631550,-11.78943266,'Perkins-Gibbs','420-861-1694'),(8,'2019-07-23 22:10:10','2019-07-23 22:10:10','628 William Cove','vpeterson@hotmail.com',-42.63481830,-174.35397875,'Hernandez, Martinez and Thomas','209.388.1011x044'),(9,'2019-07-23 22:10:54','2019-07-23 22:10:54','2702 Davis Gateway Apt. 865','stantonscott@hotmail.com',-68.95156960,177.85430906,'Moreno, Lee and Bell','(950)296-2045'),(10,'2019-07-23 22:10:54','2019-07-23 22:10:54','47850 Powell Keys','daniel62@yahoo.com',-74.63225584,92.03693145,'King Group','(005)965-7487');
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
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `person_type` int(11) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fwmwi44u55bo4rvwsv0cln012` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'2019-07-23 22:06:08','2019-07-23 22:06:08',_binary '',NULL,'jasonodonnell@yahoo.com','Jason','ODonnell','$2a$10$qMIY9367n/8nEpqPmX9OWO/ah8JH1V9FKCaGrfvDmEiO3NhlOjNyO',1,NULL),(2,'2019-07-23 22:06:08','2019-07-23 22:10:10',_binary '','4353 Lori Lakes Apt. 219','james91@jones.com','Gregory','Jones',NULL,3,'+1-572-416-9333x4102'),(3,'2019-07-23 22:06:08','2019-07-23 22:06:08',_binary '','5776 Chavez Estate Suite 098','amy96@carter-johns.com','Terri','Caldwell',NULL,3,'953.196.5923x7751'),(4,'2019-07-23 22:07:29','2019-07-23 22:07:29',_binary '','861 Jeremy Parkways','jennifer06@whitehead.com','Lisa','Griffin',NULL,3,'4980867739'),(5,'2019-07-23 22:07:29','2019-07-23 22:07:29',_binary '','9283 Glen Trafficway Suite 887','lorimueller@lang.info','Daniel','Nichols',NULL,3,'074.451.9522x5138'),(6,'2019-07-23 22:08:20','2019-07-23 22:08:20',_binary '','6413 Sarah Vista Suite 028','joan82@yahoo.com','Tyler','Neal',NULL,3,'(712)020-8388'),(7,'2019-07-23 22:08:20','2019-07-23 22:08:20',_binary '','5821 Kelly Locks','mcclurebreanna@tucker-shelton.org','Patrick','Williams',NULL,3,'(865)391-3124x351'),(8,'2019-07-23 22:10:10','2019-07-23 22:10:10',_binary '','632 Rebecca Trace Apt. 496','jeffrey82@knight.com','Karen','Aguilar',NULL,3,'(775)400-4700x9475'),(9,'2019-07-23 22:10:10','2019-07-23 22:10:54',_binary '','624 Gibson Vista','zfuentes@joseph.com','Jennifer','Guerrero',NULL,3,'7791251365'),(10,'2019-07-23 22:10:54','2019-07-23 22:10:54',_binary '','2687 Joseph Stravenue Suite 633','cunninghamconnie@frazier-robertson.org','Paula','Barnes',NULL,3,'+1-040-159-4251x89121'),(11,'2019-07-23 22:10:54','2019-07-23 22:10:54',_binary '','2186 Ross Forges','jennifernguyen@hotmail.com','Greg','Coleman',NULL,3,'332.808.8967x9328');
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
  `completed` bit(1) DEFAULT NULL,
  `end` datetime NOT NULL,
  `location_id` int(11) NOT NULL,
  `pay_rate` decimal(19,2) DEFAULT NULL,
  `person_id` int(11) NOT NULL,
  `start` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'2019-07-23 22:06:08','2019-07-23 22:06:08',_binary '\0','2019-08-09 11:44:42',2,45.00,2,'2019-08-09 10:44:42'),(2,'2019-07-23 22:06:08','2019-07-23 22:06:08',_binary '\0','2019-08-23 18:57:05',2,22.00,2,'2019-08-23 17:57:05'),(3,'2019-07-23 22:06:08','2019-07-23 22:06:08',_binary '\0','2019-09-22 00:25:07',2,46.00,2,'2019-09-21 23:25:07'),(4,'2019-07-23 22:06:08','2019-07-23 22:06:08',_binary '\0','2019-08-24 21:01:38',2,14.00,2,'2019-08-24 20:01:38'),(5,'2019-07-23 22:06:08','2019-07-23 22:06:08',_binary '\0','2019-08-31 05:15:16',1,38.00,2,'2019-08-31 04:15:16'),(6,'2019-07-23 22:07:29','2019-07-23 22:07:29',_binary '\0','2019-04-06 16:15:20',2,43.00,2,'2019-04-06 15:15:20'),(7,'2019-07-23 22:07:29','2019-07-23 22:07:29',_binary '\0','2019-09-30 13:56:36',2,50.00,2,'2019-09-30 12:56:36'),(8,'2019-07-23 22:07:29','2019-07-23 22:07:29',_binary '\0','2019-05-01 19:33:21',2,31.00,2,'2019-05-01 18:33:21'),(9,'2019-07-23 22:07:29','2019-07-23 22:07:29',_binary '\0','2019-08-29 08:49:46',4,34.00,2,'2019-08-29 07:49:46'),(10,'2019-07-23 22:07:29','2019-07-23 22:07:29',_binary '\0','2019-07-13 22:57:12',4,25.00,2,'2019-07-13 21:57:12'),(11,'2019-07-23 22:07:29','2019-07-23 22:07:29',_binary '\0','2019-10-13 12:59:18',4,24.00,2,'2019-10-13 11:59:18'),(12,'2019-07-23 22:08:20','2019-07-23 22:08:20',_binary '\0','2019-08-11 04:28:27',5,48.00,5,'2019-08-11 03:28:27'),(13,'2019-07-23 22:08:20','2019-07-23 22:08:20',_binary '\0','2019-04-06 21:02:23',5,41.00,5,'2019-04-06 20:02:23'),(14,'2019-07-23 22:08:20','2019-07-23 22:08:20',_binary '\0','2019-10-08 04:32:46',1,28.00,6,'2019-10-08 03:32:46'),(15,'2019-07-23 22:10:10','2019-07-23 22:10:10',_binary '\0','2019-10-05 03:26:07',2,26.00,3,'2019-10-05 02:26:07'),(16,'2019-07-23 22:10:10','2019-07-23 22:10:10',_binary '\0','2019-06-13 08:50:20',2,12.00,2,'2019-06-13 07:50:20'),(17,'2019-07-23 22:10:54','2019-07-23 22:10:54',_binary '\0','2019-08-06 10:03:10',7,30.00,2,'2019-08-06 09:03:10'),(18,'2019-07-23 22:10:54','2019-07-23 22:10:54',_binary '\0','2019-05-20 03:42:12',7,41.00,2,'2019-05-20 02:42:12'),(19,'2019-07-23 22:10:54','2019-07-23 22:10:54',_binary '\0','2019-06-06 19:09:25',7,16.00,2,'2019-06-06 18:09:25'),(20,'2019-07-23 22:10:54','2019-07-23 22:10:54',_binary '\0','2019-09-16 16:35:21',7,15.00,2,'2019-09-16 15:35:21'),(21,'2019-07-23 22:10:54','2019-07-23 22:10:54',_binary '\0','2019-08-20 09:41:17',7,44.00,2,'2019-08-20 08:41:17'),(22,'2019-07-23 22:10:54','2019-07-23 22:10:54',_binary '\0','2019-07-23 06:37:23',2,28.00,9,'2019-07-23 05:37:23');
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

-- Dump completed on 2019-07-23 15:12:59
