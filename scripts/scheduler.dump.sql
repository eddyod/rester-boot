-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: scheduler
-- ------------------------------------------------------
-- Server version	5.7.26-log

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'2019-09-12 22:19:29','2019-09-12 23:03:40','22751 Willis Lodge','mclay@flynn.com',31.47739101,-117.59807464,'Perez Ltd','696.508.7677'),(2,'2019-09-12 22:19:30','2019-09-12 22:19:30','76673 Scott Trail Suite 144','tamara45@hopkins.info',33.26604247,-117.43622106,'Bryant-Mckee','001-325-843-8094x102'),(3,'2019-09-12 22:19:30','2019-09-12 22:19:30','9539 Wall Shoals Suite 247','tsosa@hotmail.com',31.43098843,-118.87105430,'Garcia, Robinson and Lee','+1-275-972-3008'),(4,'2019-09-12 22:19:30','2019-09-12 22:19:30','932 Sherry Knolls Suite 311','cbrock@leonard.biz',32.55848011,-118.79896794,'Martin Inc','433-962-8109x02674'),(5,'2019-09-12 22:19:30','2019-09-12 22:19:30','499 Robert Prairie Suite 515','torresstacey@miller-bowers.com',32.91537461,-118.16191612,'Allen PLC','001-898-152-5937x03851'),(6,'2019-09-12 22:29:07','2019-09-12 22:29:07','9393 Hart Burg','juliebrown@yahoo.com',32.13603924,-117.87975222,'Williams-Potter','+1-536-409-8758x7796'),(7,'2019-09-12 22:29:07','2019-09-12 22:29:07','78400 Vincent Summit Apt. 406','amy10@hotmail.com',31.20202796,-118.80901353,'King, Hart and Hogan','(625)733-0843x86103'),(8,'2019-09-12 22:29:07','2019-09-12 22:29:07','786 Mathis Path','justin63@walsh.net',33.08065610,-118.15708303,'Neal LLC','7752703853'),(9,'2019-09-12 22:29:08','2019-09-12 22:29:08','028 Le Landing','laurablanchard@gmail.com',31.04614471,-116.68448331,'Williamson, Jackson and Taylor','+1-762-769-7437x578'),(10,'2019-09-12 22:29:08','2019-09-12 22:29:08','115 Graham Flats','lamberterica@gmail.com',33.44450024,-116.81581321,'Mitchell, Miller and Dawson','+1-905-402-9006x803'),(11,'2019-09-12 22:29:08','2019-09-12 22:29:08','260 Adam Cliffs','krauseadam@boyer.biz',33.82937597,-117.16610066,'Garcia LLC','172.746.4023x5567'),(12,'2019-09-12 22:29:08','2019-09-12 22:29:08','77782 Cooke Well Suite 237','william75@carroll.com',33.79356443,-117.70316373,'Smith-Stone','851-454-5358x1270'),(13,'2019-09-12 22:29:08','2019-09-12 22:29:08','07823 Hart Cape','craig92@gmail.com',31.43809399,-117.84807668,'Guerra-Wade','394.460.4027'),(14,'2019-09-12 22:29:08','2019-09-12 22:29:08','99263 Clark Ports','stevenevans@lopez.biz',31.99216009,-117.26104949,'Costa-Mccormick','(035)455-0934x534'),(15,'2019-09-12 22:29:08','2019-09-12 22:29:08','7395 Meghan Cliffs Suite 374','ayersangela@carroll-fernandez.com',32.81925804,-117.02813303,'Davis-Lopez','(474)374-0811'),(16,'2019-09-12 22:29:08','2019-09-12 22:29:08','7033 Eric Shoal Apt. 128','qthomas@cochran.info',33.01531955,-118.33660738,'Stevens, Hubbard and Leblanc','001-265-707-5247x99767'),(17,'2019-09-12 22:29:08','2019-09-12 22:29:08','6076 Brian Harbor','imeza@yahoo.com',31.09604137,-117.09777728,'Douglas-Berry','(083)372-0613x36967'),(18,'2019-09-12 22:29:09','2019-09-12 22:29:09','9636 Lisa Ranch Apt. 106','christopherbenson@yahoo.com',32.94736850,-116.39232561,'Webb-Roberts','960-381-0650');
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
  `school_id` int(11) DEFAULT NULL,
  `token` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fwmwi44u55bo4rvwsv0cln012` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'2019-09-12 22:19:17','2019-09-12 22:19:17',_binary '',NULL,'jasonodonnell@yahoo.com','Jason','ODonnell','$2a$10$hfbI5ZlA310t5utSJAdVzuYPXuH40nGb7lW94j26lstRK5AR2OmKq',999,NULL,NULL,NULL),(2,'2019-09-12 22:19:29','2019-09-12 22:26:55',_binary '','3879 Gonzalez Pass','eharrington@douglas.com','Kyle','Washington',NULL,2,'223-280-8339x44122',5,NULL),(3,'2019-09-12 22:19:29','2019-09-12 22:19:29',_binary '','65678 Newman Trail Apt. 416','elewis@gmail.com','Alejandro','Villarreal',NULL,1,'3677231342',NULL,NULL),(4,'2019-09-12 22:19:30','2019-09-12 22:19:30',_binary '','87709 Hicks Row Suite 708','herreraandrew@yahoo.com','Cynthia','Cohen',NULL,1,'493.337.5176',NULL,NULL),(5,'2019-09-12 22:19:30','2019-09-12 22:19:30',_binary '','84401 Michael Meadow Apt. 865','xwilliams@bishop.net','Amy','Garcia',NULL,1,'243-783-5856x789',NULL,NULL),(6,'2019-09-12 22:19:30','2019-09-12 22:19:30',_binary '','93621 Butler Mills Suite 342','beckerjames@ramos.com','Patricia','Fisher',NULL,1,'(503)269-5626',NULL,NULL),(7,'2019-09-12 22:29:07','2019-09-12 22:29:07',_binary '','63841 Stewart Lakes','jameskayla@thomas-coleman.com','Michael','Guzman',NULL,1,'001-608-714-7290x408',NULL,NULL),(8,'2019-09-12 22:29:07','2019-09-12 22:29:07',_binary '','902 James Expressway Suite 966','brendajacobs@yahoo.com','Jonathan','Ray',NULL,1,'+1-677-488-0302',NULL,NULL),(9,'2019-09-12 22:29:07','2019-09-12 22:29:07',_binary '','846 Dyer Lodge','emilydaniel@yahoo.com','Joseph','Walker',NULL,1,'8734167829',NULL,NULL),(10,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '','35046 Steven Branch Suite 860','lauren81@yahoo.com','Mark','Brown',NULL,1,'500-019-9812',NULL,NULL),(11,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '','330 Cole Valley Apt. 402','powellmatthew@fowler.com','Clarence','Pittman',NULL,1,'366.089.8624x526',NULL,NULL),(12,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '','7243 Wheeler Ports','pamela41@dixon.com','Shelly','Rodriguez',NULL,1,'407-358-3449x8218',NULL,NULL),(13,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '','007 Elizabeth Plain Suite 928','brianna39@gmail.com','Laura','Adams',NULL,1,'+1-943-670-2320x607',NULL,NULL),(14,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '','7079 Jeffery Forest Suite 830','michellesmith@hotmail.com','Joanne','Lucero',NULL,1,'(782)475-9838x3633',NULL,NULL),(15,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '','562 Jones Point Suite 944','alexisleon@lindsey-cooper.biz','Benjamin','Fowler',NULL,1,'(964)686-2807x3793',NULL,NULL),(16,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '','877 Conner Shores Suite 071','cathy57@allen-avery.info','Diamond','Hicks',NULL,1,'001-942-415-0079x60320',NULL,NULL),(17,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '','2413 West Spurs Suite 927','john53@cook.org','Christopher','Clark',NULL,1,'001-751-008-8482x09235',NULL,NULL),(18,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '','7256 Mills Cape','kelly32@yahoo.com','Rebecca','Ware',NULL,1,'001-546-663-5043',NULL,NULL),(19,'2019-09-12 22:29:09','2019-09-12 22:29:09',_binary '','3604 Crystal Pines Apt. 119','ana45@daniels.com','Michael','Brown',NULL,1,'007-218-5927x8638',NULL,NULL),(22,'2019-09-13 16:52:47','2019-09-13 16:56:06',_binary '','123 Main St','eddyod@yahoo.com','Eddy','ODonnell',NULL,2,'619-555-1212',6,NULL);
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
  KEY `FKq3iry2bh2tssqn1nfoakqcs4m` (`location_id`),
  KEY `FK1a66og66fjg2trplqyqccvoib` (`person_id`),
  CONSTRAINT `FK1a66og66fjg2trplqyqccvoib` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKq3iry2bh2tssqn1nfoakqcs4m` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'2019-09-12 22:29:07','2019-09-15 16:43:09',_binary '\0','2019-08-31 22:14:00',5,43.00,7,'2019-08-31 21:14:00'),(2,'2019-09-12 22:29:07','2019-09-12 22:29:07',_binary '\0','2019-06-06 12:15:08',5,19.00,7,'2019-06-06 11:15:08'),(3,'2019-09-12 22:29:07','2019-09-12 22:29:07',_binary '\0','2019-08-11 08:01:54',4,26.00,6,'2019-08-11 07:01:54'),(4,'2019-09-12 22:29:07','2019-09-15 16:50:36',_binary '','2020-01-04 06:37:00',4,31.00,6,'2020-01-04 05:37:00'),(5,'2019-09-12 22:29:07','2019-09-12 22:29:07',_binary '\0','2019-11-19 02:45:31',6,42.00,8,'2019-11-19 01:45:31'),(6,'2019-09-12 22:29:07','2019-09-12 22:29:07',_binary '\0','2020-01-03 07:59:39',6,32.00,8,'2020-01-03 06:59:39'),(7,'2019-09-12 22:29:07','2019-09-12 22:29:07',_binary '\0','2019-05-28 08:57:03',6,34.00,8,'2019-05-28 07:57:03'),(8,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2020-01-06 07:39:36',6,31.00,8,'2020-01-06 06:39:36'),(9,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-09-10 15:52:48',6,17.00,8,'2019-09-10 14:52:48'),(10,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-12-15 10:13:41',7,41.00,9,'2019-12-15 09:13:41'),(11,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-12-21 00:06:57',7,43.00,9,'2019-12-20 23:06:57'),(12,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-11-10 04:32:00',7,38.00,9,'2019-11-10 03:32:00'),(13,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2020-01-08 08:07:54',5,24.00,5,'2020-01-08 07:07:54'),(14,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2020-01-09 16:53:10',5,38.00,5,'2020-01-09 15:53:10'),(15,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-07-27 02:55:34',5,21.00,11,'2019-07-27 01:55:34'),(16,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-07-06 08:20:03',5,41.00,11,'2019-07-06 07:20:03'),(17,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-06-15 14:09:16',5,29.00,11,'2019-06-15 13:09:16'),(18,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-07-07 06:53:26',5,36.00,11,'2019-07-07 05:53:26'),(19,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-06-21 10:06:40',5,45.00,11,'2019-06-21 09:06:40'),(20,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-09-13 01:26:36',7,23.00,10,'2019-09-13 00:26:36'),(21,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-06-22 10:31:51',7,50.00,10,'2019-06-22 09:31:51'),(22,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-12-24 21:03:43',7,30.00,10,'2019-12-24 20:03:43'),(23,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-08-04 02:17:00',7,37.00,10,'2019-08-04 01:17:00'),(24,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-06-20 19:57:24',7,29.00,10,'2019-06-20 18:57:24'),(25,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-10-26 13:15:47',12,19.00,12,'2019-10-26 12:15:47'),(26,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-10-21 21:02:29',12,26.00,12,'2019-10-21 20:02:29'),(27,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-10-08 04:33:22',2,15.00,15,'2019-10-08 03:33:22'),(28,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-09-06 09:34:36',2,22.00,15,'2019-09-06 08:34:36'),(29,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-06-12 16:55:43',2,42.00,15,'2019-06-12 15:55:43'),(30,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-07-30 12:16:46',2,49.00,15,'2019-07-30 11:16:46'),(31,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-09-01 09:34:50',2,48.00,15,'2019-09-01 08:34:50'),(32,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2020-01-03 18:55:00',11,39.00,12,'2020-01-03 17:55:00'),(33,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-07-03 06:23:59',11,50.00,12,'2019-07-03 05:23:59'),(34,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-05-18 02:29:32',11,34.00,12,'2019-05-18 01:29:32'),(35,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-09-02 09:50:54',11,38.00,12,'2019-09-02 08:50:54'),(36,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-07-15 09:09:57',11,26.00,12,'2019-07-15 08:09:57'),(37,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-05-23 20:18:12',6,46.00,14,'2019-05-23 19:18:12'),(38,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-06-14 10:34:54',6,39.00,14,'2019-06-14 09:34:54'),(39,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-09-09 21:26:11',6,23.00,14,'2019-09-09 20:26:11'),(40,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-06-19 12:51:59',6,43.00,14,'2019-06-19 11:51:59'),(41,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-12-13 15:03:36',6,29.00,14,'2019-12-13 14:03:36'),(42,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-05-31 06:41:21',4,37.00,15,'2019-05-31 05:41:21'),(43,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-07-04 11:27:18',4,44.00,15,'2019-07-04 10:27:18'),(44,'2019-09-12 22:29:08','2019-09-12 22:29:08',_binary '\0','2019-06-08 09:21:21',4,13.00,15,'2019-06-08 08:21:21'),(45,'2019-09-12 22:29:09','2019-09-12 22:29:09',_binary '\0','2019-08-10 09:02:04',4,31.00,15,'2019-08-10 08:02:04'),(46,'2019-09-12 22:29:09','2019-09-12 22:29:09',_binary '\0','2019-09-17 10:59:59',11,37.00,14,'2019-09-17 09:59:59'),(47,'2019-09-12 22:29:09','2019-09-12 22:29:09',_binary '\0','2019-06-26 11:02:50',11,44.00,14,'2019-06-26 10:02:50'),(48,'2019-09-12 22:29:09','2019-09-12 22:29:09',_binary '\0','2019-10-24 23:32:08',11,23.00,14,'2019-10-24 22:32:08'),(49,'2019-09-12 22:29:09','2019-09-12 22:29:09',_binary '\0','2019-06-29 05:33:27',11,37.00,14,'2019-06-29 04:33:27'),(50,'2019-09-12 22:29:09','2019-09-12 22:29:09',_binary '','2019-08-13 23:48:49',11,38.00,14,'2019-08-13 22:48:49');
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

-- Dump completed on 2019-09-16 11:56:58
