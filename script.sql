CREATE DATABASE  IF NOT EXISTS `bugtracker` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bugtracker`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: bugtracker
-- ------------------------------------------------------
-- Server version	5.1.73-community

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
-- Table structure for table `bug`
--

DROP TABLE IF EXISTS `bug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bug` (
  `ID` int(11) NOT NULL,
  `DATE_SUBMITTED` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `SUPPORT_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKmw0by7p5qmtlugp1spxgb3a46` (`SUPPORT_ID`),
  KEY `FKmsapk80pequuvr9vrj76b4x6s` (`USER_ID`),
  CONSTRAINT `FKmsapk80pequuvr9vrj76b4x6s` FOREIGN KEY (`USER_ID`) REFERENCES `employee` (`ID`),
  CONSTRAINT `FKmw0by7p5qmtlugp1spxgb3a46` FOREIGN KEY (`SUPPORT_ID`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bug`
--

LOCK TABLES `bug` WRITE;
/*!40000 ALTER TABLE `bug` DISABLE KEYS */;
INSERT INTO `bug` (`ID`, `DATE_SUBMITTED`, `DESCRIPTION`, `STATUS`, `TITLE`, `SUPPORT_ID`, `USER_ID`) VALUES (1,'2016-05-19 16:51:54','description1','CLOSED','title1',1,2),(2,'2016-05-19 16:53:31','description2','CLOSED','title2',3,4),(6,'2016-05-20 15:05:54','sf','CLOSED','This is 1st title of today',1,2),(7,'2016-05-20 15:11:49','sdfsdfsdf','CLOSED','This is 2nd title of today',3,2),(8,'2016-05-23 17:19:49','Drawer key is missing','CLOSED','Key missing',3,2),(9,'2016-05-23 17:20:15','Desktop mouse not working','OPEN','mouse is not working',NULL,4),(10,'2016-05-23 17:20:58','Desktop keyboard missing','CLOSED','Keyboard missing',3,4),(11,'2016-05-23 17:21:20','No internet access on desktop','OPEN','No internet',NULL,2),(12,'2016-05-23 17:21:51','Not working','CLOSED','Telephone',3,4),(13,'2016-05-23 18:44:18','blah','IN_PROGRESS','blah',1,2);
/*!40000 ALTER TABLE `bug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bug_seq`
--

DROP TABLE IF EXISTS `bug_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bug_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bug_seq`
--

LOCK TABLES `bug_seq` WRITE;
/*!40000 ALTER TABLE `bug_seq` DISABLE KEYS */;
INSERT INTO `bug_seq` (`next_val`) VALUES (14);
/*!40000 ALTER TABLE `bug_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `ROLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`ID`, `NAME`, `PASSWORD`, `ROLE`) VALUES (1,'support1','1000:1eb673858f264f05e1ea882f25720a96:f11e7b5df7f1b46ba5d969dcc749a70ad7123420881b1e7cf862ebaeaaa0da5f6848b0f2e0d3e69e94e42a688444dbf5ff6fc38abe989ca8d3cb2ee4afb8e65c','SUPPORT'),(2,'user1','1000:7891d6b637eda6b286096d2945fd0ad7:bb064cb3f1149c39e13d9fd707c7d70aca6fff5a5eab3438ccd3115e1220db82274cc259ecb24c4e88868d5de5aeaf70e5b08176a2371fba650497757b826b2d','USER'),(3,'support2','1000:004e2c60a349b2c9441ed8d2fe2f933b:3b62be88a691abfa766077bccf82bb0659c25d511b0a45abbc77c14c93e5d534efe289fb390cfa70213bf0ce1996e2e41dd076b25d9ce6692ef6254f9e7151cf','SUPPORT'),(4,'user2','1000:1e6448c41ed7e339f8b05a623bc97516:aaf9114f26b1a01a09698d1f0ff2a9caa44443b0ea4e1e3fa86ce211a1dbd71572485055e3ee1bf29110f9f246f25afd3d72771de15a7fbe22e05f60e7b019c8','USER'),(5,'admin','1000:a4f06ced25df4ff104db2236843a36ca:6139013924169de0058502e07c4797bfa97200cf8aeb998410fb26365938499801b76c74b219ba748503668d2b95a6e07db9a178c12ab998f86ae7fb88fb4759','ADMIN');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_seq`
--

DROP TABLE IF EXISTS `employee_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_seq`
--

LOCK TABLES `employee_seq` WRITE;
/*!40000 ALTER TABLE `employee_seq` DISABLE KEYS */;
INSERT INTO `employee_seq` (`next_val`) VALUES (5);
/*!40000 ALTER TABLE `employee_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bugtracker'
--

--
-- Dumping routines for database 'bugtracker'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-23 19:17:46
