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
INSERT INTO `bug` VALUES (1,'2016-05-19 16:51:54','description1','CLOSED','title1',1,2),(2,'2016-05-19 16:53:31','description2','CLOSED','title2',3,4),(6,'2016-05-20 15:05:54','sf','OPEN','This is 1st title of today',NULL,2),(7,'2016-05-20 15:11:49','sdfsdfsdf','OPEN','This is 2nd title of today',NULL,2);
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
INSERT INTO `bug_seq` VALUES (8);
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
INSERT INTO `employee` VALUES (1,'support1','password','SUPPORT'),(2,'user1','password','USER'),(3,'support2','password','SUPPORT'),(4,'user2','password','USER'),(5,'admin','admin','ADMIN');
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
INSERT INTO `employee_seq` VALUES (5);
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

-- Dump completed on 2016-05-20 19:55:55
