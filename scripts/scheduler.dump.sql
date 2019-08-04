-- MySQL dump 10.16  Distrib 10.1.38-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: 192.168.1.12    Database: scheduler
-- ------------------------------------------------------
-- Server version	5.7.25-log

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
INSERT INTO `location` VALUES (1,'2019-07-23 22:06:08','2019-08-02 17:16:39','001 Mary Bypass','christensenchristopher@jones.info',32.82832590,-117.25585400,'Macdonald-Brown','+1-708-256-8877x5677'),(2,'2019-07-23 22:06:08','2019-07-30 03:18:11','Kuningan City Mall Ground','dacosta@yahoo.com',-6.22454450,106.82672840,'Outback steakhouse, crikey','001-366-965-7674x89174'),(3,'2019-07-23 22:07:29','2019-07-23 22:31:11','77229 Bush Lodge Suite 291','erikajensen@gmail.com',79.63517079,-158.11579557,'Marshall-Escobar','3795346283'),(4,'2019-07-23 22:07:29','2019-07-23 22:07:29','1213 Norman Fort Suite 296','barronjames@hotmail.com',55.70983743,-2.04462414,'Schultz, Erickson and Bowers','+1-320-724-7453x805'),(5,'2019-07-23 22:08:20','2019-07-24 12:43:28','718 Rachel Springs','josephlowe@hawkins-howard.com',17.36903368,33.40765736,'Armstrong Ltd','+1-220-388-3652'),(6,'2019-07-23 22:08:20','2019-07-23 22:08:20','25483 Kennedy Courts Suite 968','sharon86@gmail.com',50.79979907,-43.93922048,'Hall LLC','(942)696-4738'),(7,'2019-07-23 22:10:10','2019-07-23 23:06:09','4057 Brant St','dawngomez@cox-dyer.info',32.75107130,-117.16917260,'Perkins-Gibbs','420-861-1694'),(8,'2019-07-23 22:10:10','2019-07-23 22:10:10','628 William Cove','vpeterson@hotmail.com',-42.63481830,-174.35397875,'Hernandez, Martinez and Thomas','209.388.1011x044'),(9,'2019-07-23 22:10:54','2019-07-23 22:10:54','2702 Davis Gateway Apt. 865','stantonscott@hotmail.com',-68.95156960,177.85430906,'Moreno, Lee and Bell','(950)296-2045'),(10,'2019-07-23 22:10:54','2019-07-23 22:10:54','47850 Powell Keys','daniel62@yahoo.com',-74.63225584,92.03693145,'King Group','(005)965-7487');
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
  `token` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fwmwi44u55bo4rvwsv0cln012` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'2019-07-23 22:06:08','2019-07-23 22:06:08','',NULL,'jasonodonnell@yahoo.com','Jason','ODonnell','$2a$10$qMIY9367n/8nEpqPmX9OWO/ah8JH1V9FKCaGrfvDmEiO3NhlOjNyO',1,NULL,NULL),(2,'2019-07-23 22:06:08','2019-07-23 22:10:10','','4353 Lori Lakes Apt. 219','james91@jones.com','Gregory','Jones',NULL,3,'+1-572-416-9333x4102',NULL),(3,'2019-07-23 22:06:08','2019-07-23 22:06:08','','5776 Chavez Estate Suite 098','amy96@carter-johns.com','Terri','Caldwell',NULL,3,'953.196.5923x7751',NULL),(4,'2019-07-23 22:07:29','2019-07-23 22:07:29','','861 Jeremy Parkways','jennifer06@whitehead.com','Lisa','Griffin',NULL,3,'4980867739',NULL),(5,'2019-07-23 22:07:29','2019-07-23 22:07:29','','9283 Glen Trafficway Suite 887','lorimueller@lang.info','Daniel','Nichols',NULL,3,'074.451.9522x5138',NULL),(6,'2019-07-23 22:08:20','2019-07-23 22:08:20','','6413 Sarah Vista Suite 028','joan82@yahoo.com','Tyler','Neal',NULL,3,'(712)020-8388',NULL),(7,'2019-07-23 22:08:20','2019-07-23 22:08:20','','5821 Kelly Locks','mcclurebreanna@tucker-shelton.org','Patrick','Williams',NULL,3,'(865)391-3124x351',NULL),(8,'2019-07-23 22:10:10','2019-07-23 22:10:10','','632 Rebecca Trace Apt. 496','jeffrey82@knight.com','Karen','Aguilar',NULL,3,'(775)400-4700x9475',NULL),(9,'2019-07-23 22:10:10','2019-07-23 22:10:54','','624 Gibson Vista','zfuentes@joseph.com','Jennifer','Guerrero',NULL,3,'7791251365',NULL),(10,'2019-07-23 22:10:54','2019-07-23 22:10:54','','2687 Joseph Stravenue Suite 633','cunninghamconnie@frazier-robertson.org','Paula','Barnes',NULL,3,'+1-040-159-4251x89121',NULL),(11,'2019-07-23 22:10:54','2019-07-23 22:10:54','','2186 Ross Forges','jennifernguyen@hotmail.com','Greg','Coleman',NULL,3,'332.808.8967x9328',NULL);
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
  `location_id` bigint(20) NOT NULL,
  `pay_rate` decimal(19,2) DEFAULT NULL,
  `person_id` bigint(20) NOT NULL,
  `start` datetime NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK__S_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT `FK__S_location` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-04  7:41:30
