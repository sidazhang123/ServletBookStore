-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bookstore
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `price` float(8,2) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `categoryId` varchar(100) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id_fk` (`categoryId`),
  CONSTRAINT `category_id_fk` FOREIGN KEY (`categoryId`) REFERENCES `categorys` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES ('11ba463e-3fea-4468-b0d3-21662b074d45','gdgfd','gdfg',11.00,'2016-09-26','536a8504-4c4c-4a7a-9e16-657f4382b4b1.','gfdgdf','1681e330-aa8f-4a49-bbbc-077e7b93b518','22979dae-464b-440e-b432-e5a3a6cf2c05'),('1254697a-ff26-4caf-8691-922e2a49c904','1111','1212',10.00,'2016-09-24','ccfdccbe-e6cc-4f1b-869b-7dfca4f514fc.','','1681e330-aa8f-4a49-bbbc-077e7b93b518','22979dae-464b-440e-b432-e5a3a6cf2c05'),('19e1b370-032f-4b3f-93b7-0cc1225604c4','fuck','gdfgdf',23.00,'2016-09-24','fcba5b2c-713a-4c8d-9314-ca951bd640fd.jpeg','fsdgfdsg','78c7cfaf-3189-4d47-a4fe-18dcf3689a25','f9714954-11b0-4509-8d15-857494015ae9'),('5e44e740-8f36-4a4d-9583-fea77c5b78d2','fgdfd','gfdhdf',3.00,'2016-09-24','ea4f3cbe-8760-4070-8f39-800bc30d9669.','gsdgs','1681e330-aa8f-4a49-bbbc-077e7b93b518','f9714954-11b0-4509-8d15-857494015ae9'),('6a054297-b70b-44eb-9928-d407d4ac3fd4','gfgdfg','gfdg',42.00,'2016-09-24','c75fd5f1-9fe9-447f-972c-6edfd063f28e.','fgdgdf','1681e330-aa8f-4a49-bbbc-077e7b93b518','f9714954-11b0-4509-8d15-857494015ae9'),('935fb1ba-f18d-42f2-abc6-ccedd5379066','jkyukyu','iuyi',13.00,'2016-09-26','236f2480-7a4a-4760-ac8e-e673fa1820d7.jpeg','jyhjghjgfd','78c7cfaf-3189-4d47-a4fe-18dcf3689a25','f9714954-11b0-4509-8d15-857494015ae9'),('9d746382-496e-4757-8e36-614385c5762c','tewtwe','tewtwe',12.00,'2016-09-26','33426755-b068-4469-931f-6b11367831dc.jpeg','gdfgfdg','1681e330-aa8f-4a49-bbbc-077e7b93b518','22979dae-464b-440e-b432-e5a3a6cf2c05');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `price` float(8,2) DEFAULT NULL,
  `number` varchar(100) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES ('1254697a-ff26-4caf-8691-922e2a49c904','1111',10.00,'1','f9714954-11b0-4509-8d15-857494015ae9'),('19e1b370-032f-4b3f-93b7-0cc1225604c4','fuck',23.00,'1','f9714954-11b0-4509-8d15-857494015ae9'),('6a054297-b70b-44eb-9928-d407d4ac3fd4','gfgdfg',42.00,'1','f9714954-11b0-4509-8d15-857494015ae9'),('9d746382-496e-4757-8e36-614385c5762c','tewtwe',12.00,'1','f9714954-11b0-4509-8d15-857494015ae9'),('935fb1ba-f18d-42f2-abc6-ccedd5379066','jkyukyu',13.00,'1','ba3e172b-85f0-4e05-aa7e-1c4e963bd722'),('6a054297-b70b-44eb-9928-d407d4ac3fd4','gfgdfg',42.00,'1','ba3e172b-85f0-4e05-aa7e-1c4e963bd722'),('9d746382-496e-4757-8e36-614385c5762c','tewtwe',12.00,'1','ba3e172b-85f0-4e05-aa7e-1c4e963bd722');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorys`
--

DROP TABLE IF EXISTS `categorys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorys` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `des` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorys`
--

LOCK TABLES `categorys` WRITE;
/*!40000 ALTER TABLE `categorys` DISABLE KEYS */;
INSERT INTO `categorys` VALUES ('1681e330-aa8f-4a49-bbbc-077e7b93b518','book','ffdsf'),('78c7cfaf-3189-4d47-a4fe-18dcf3689a25','fdsfdsfdsfsd','gsdfds');
/*!40000 ALTER TABLE `categorys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(20) NOT NULL,
  `code` varchar(200) DEFAULT NULL,
  `actived` bit(1) DEFAULT NULL,
  `nickname` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `yearofbirth` varchar(100) NOT NULL,
  `creditcardno` varchar(100) NOT NULL,
  `isbanned` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `photo` (`phone`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES ('0d6c3edd-4069-4848-a56f-119cc2f48a29','23sdfsdf','fsaf','sdfdsf','dsfdsf','dsfsdfsdf','1a1304f2-c847-485f-bd28-a40a0511b3b9','\0','dsfdsf','dsfdsfa','dsfsf','sdfdsf','dsfsdfsdf','\0'),('57ba0eb9-4645-42bb-a83c-62a8162489a5','admin1','admin','','','fgfdg@gmail.com','e03c9ced-a820-424b-8de3-68f481c9cd31','\0','','','','','','\0'),('a9d85286-1756-4755-aca8-f300f31e8f9d','abc','123','213213','dsfdsf','zwh88439507@163.com','ba3e172b-85f0-4e05-aa7e-1c4e963bd722','','sdf','sdf','dsaf','1990','213123','\0'),('dc85c76d-0421-446a-a7f9-de56be368d37','pkwccheng','dazar123','1234','fastidi','pkwccheng@gmail.com','f9714954-11b0-4509-8d15-857494015ae9','','da','youdad','d','2000','2147483647','\0'),('dd53f1d4-130c-4cfa-b354-3256a6a82df8','gfdgfd','gdfgd','54765','hfghfg','pkwccheng1@gmail.com','22979dae-464b-440e-b432-e5a3a6cf2c05','','jhg','htjh','jhgj','4444','76890876578','\0');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `functions`
--

DROP TABLE IF EXISTS `functions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `functions` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `uri` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `functions`
--

LOCK TABLES `functions` WRITE;
/*!40000 ALTER TABLE `functions` DISABLE KEYS */;
INSERT INTO `functions` VALUES ('1','homepage','/BookStore/manage/index.jsp'),('2','info','/BookStore/manage/message.jsp'),('3','addcategory','/BookStore/manage/addCategory.jsp'),('4','querycategory','/BookStore/servlet/ManageServlet?op=listCategories'),('5','addbook','/BookStore/servlet/ManageServlet?op=addBookUI'),('6','querybook','/BookStore/servlet/ManageServlet?op=listBooks');
/*!40000 ALTER TABLE `functions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `mark` varchar(10) NOT NULL,
  `bookid` varchar(100) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `timestamp` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES ('buy','935fb1ba-f18d-42f2-abc6-ccedd5379066','f9714954-11b0-4509-8d15-857494015ae9','20160928_141845'),('buy','935fb1ba-f18d-42f2-abc6-ccedd5379066','f9714954-11b0-4509-8d15-857494015ae9','20160928_141903'),('buy','6a054297-b70b-44eb-9928-d407d4ac3fd4','f9714954-11b0-4509-8d15-857494015ae9','20160928_141903'),('rmv','935fb1ba-f18d-42f2-abc6-ccedd5379066','f9714954-11b0-4509-8d15-857494015ae9','20160928_142059'),('rmv','5e44e740-8f36-4a4d-9583-fea77c5b78d2','f9714954-11b0-4509-8d15-857494015ae9','20160928_142101'),('buy','1254697a-ff26-4caf-8691-922e2a49c904','22979dae-464b-440e-b432-e5a3a6cf2c05','20160928_142238');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitems`
--

DROP TABLE IF EXISTS `orderitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitems` (
  `id` varchar(100) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `price` float(8,2) DEFAULT NULL,
  `ordernum` varchar(100) DEFAULT NULL,
  `bookid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ordernum_fk` (`ordernum`),
  KEY `bookid_fk` (`bookid`),
  CONSTRAINT `bookid_fk` FOREIGN KEY (`bookid`) REFERENCES `books` (`id`),
  CONSTRAINT `ordernum_fk` FOREIGN KEY (`ordernum`) REFERENCES `orders` (`ordernum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems`
--

LOCK TABLES `orderitems` WRITE;
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
INSERT INTO `orderitems` VALUES ('01142c1e-3cfd-49e0-b2e4-eafc6c5ce44e',1,10.00,'2016092800000000003','1254697a-ff26-4caf-8691-922e2a49c904'),('0f6a83a7-cc6a-456d-a577-ba9bb339873f',1,21321.00,'2016092600000000002','1254697a-ff26-4caf-8691-922e2a49c904'),('16ba648d-9db5-4f00-8725-f28f0d2ff0c4',2,26.00,'2016092800000000004','935fb1ba-f18d-42f2-abc6-ccedd5379066'),('18d02a47-f0a1-41b2-80cb-4e964e7a33bf',1,21321.00,'2016092600000000005','1254697a-ff26-4caf-8691-922e2a49c904'),('1a70d0d1-c551-412b-9fb6-9509830489e3',1,11.00,'2016092800000000001','11ba463e-3fea-4468-b0d3-21662b074d45'),('1cd05721-8bbe-43cb-b060-cdc8d29ec58c',1,12.00,'2016092800000000001','9d746382-496e-4757-8e36-614385c5762c'),('1eaa3e47-588a-4dc0-bb9a-290cbcb5f0b5',1,21321.00,'2016092600000000006','1254697a-ff26-4caf-8691-922e2a49c904'),('2580db62-0da3-4517-8a88-a674b97f862a',1,765756.00,'2016092600000000011','935fb1ba-f18d-42f2-abc6-ccedd5379066'),('33dc71e4-d9d8-40bc-9dd5-637d0d979237',1,13.00,'2016092800000000005','935fb1ba-f18d-42f2-abc6-ccedd5379066'),('3468bedf-60bf-4cf1-ba41-742edf7b9624',2,26.00,'2016092700000000005','935fb1ba-f18d-42f2-abc6-ccedd5379066'),('3687f719-d78e-4837-9745-c968410e8d69',1,21321.00,'2016092600000000009','1254697a-ff26-4caf-8691-922e2a49c904'),('37cc685d-7ca1-41bc-b2f2-fc73d72b6b03',1,123.00,'2016092600000000013','19e1b370-032f-4b3f-93b7-0cc1225604c4'),('3b536aae-8b14-4b10-bca7-e4465e0e02ee',1,42.00,'2016092800000000005','6a054297-b70b-44eb-9928-d407d4ac3fd4'),('3ce961e6-e865-4982-ab4c-a19d136e4b17',1,12.00,'2016092600000000012','9d746382-496e-4757-8e36-614385c5762c'),('3fe9b850-db21-40a1-a991-4b9e1f4dde44',1,13.00,'2016092800000000001','935fb1ba-f18d-42f2-abc6-ccedd5379066'),('54d74d41-22a2-427b-8584-31fb1a624f8a',2,2222.00,'2016092700000000002','11ba463e-3fea-4468-b0d3-21662b074d45'),('728f01d8-f66f-469d-ab55-bc012e2868ae',3,3333.00,'2016092700000000001','11ba463e-3fea-4468-b0d3-21662b074d45'),('75a519e9-8d39-40fb-8b04-cf0271f047e3',1,42.00,'2016092800000000002','6a054297-b70b-44eb-9928-d407d4ac3fd4'),('7de784ef-3789-4843-8767-a2b2cc7baeb1',1,10.00,'2016092800000000001','1254697a-ff26-4caf-8691-922e2a49c904'),('816dab3c-acdd-44e3-a643-2f14474db978',1,42.00,'2016092800000000003','6a054297-b70b-44eb-9928-d407d4ac3fd4'),('8284c378-d94b-48c4-8573-8db11db0056f',1,42.00,'2016092800000000001','6a054297-b70b-44eb-9928-d407d4ac3fd4'),('8806aefc-3909-4651-85ce-2b5456c78cca',1,21321.00,'2016092600000000007','1254697a-ff26-4caf-8691-922e2a49c904'),('92c049a0-e349-455c-a946-cea338e2ec46',1,3.00,'2016092800000000002','5e44e740-8f36-4a4d-9583-fea77c5b78d2'),('99cfafd9-85b2-427d-b9de-27e7fd797c55',1,3.00,'2016092800000000003','5e44e740-8f36-4a4d-9583-fea77c5b78d2'),('a96cad14-2a03-47c2-b16c-b433f194a06f',1,23.00,'2016092800000000002','19e1b370-032f-4b3f-93b7-0cc1225604c4'),('acabefb2-2b1f-4dfd-91ea-e548cda8cf5d',1,21321.00,'2016092600000000010','1254697a-ff26-4caf-8691-922e2a49c904'),('af00fcb8-c7c0-4a04-9761-f31c4ae38c6b',1,23.00,'2016092800000000001','19e1b370-032f-4b3f-93b7-0cc1225604c4'),('b0971f9a-ffbd-46a6-b305-100d0f248f77',1,21321.00,'2016092600000000004','1254697a-ff26-4caf-8691-922e2a49c904'),('b1657d60-abd6-4932-8a40-361ead748760',1,21321.00,'2016092600000000001','1254697a-ff26-4caf-8691-922e2a49c904'),('b3709025-099c-493e-a933-5310659f537a',1,21321.00,'2016092600000000003','1254697a-ff26-4caf-8691-922e2a49c904'),('b6d24020-ee3a-4e79-b159-2fc15743c0d1',1,21321.00,'2016092600000000008','1254697a-ff26-4caf-8691-922e2a49c904'),('c333a1d7-de61-4331-898c-3b77249a07e7',1,23.00,'2016092800000000003','19e1b370-032f-4b3f-93b7-0cc1225604c4'),('c360cf97-c741-4e53-9f44-51c311f2df48',1,10.00,'2016092800000000002','1254697a-ff26-4caf-8691-922e2a49c904'),('cadb23eb-f6c1-46a7-9c60-ab4bfdf05265',1,12.00,'2016092800000000003','9d746382-496e-4757-8e36-614385c5762c'),('d4eb53e1-f383-4029-bbd3-0c1090d46d0e',1,13.00,'2016092800000000002','935fb1ba-f18d-42f2-abc6-ccedd5379066'),('db5eefba-0861-4b97-8943-96bf7423139e',1,3.00,'2016092800000000001','5e44e740-8f36-4a4d-9583-fea77c5b78d2'),('de80b52a-fb82-4f11-b549-bc267b6f012f',1,1323.00,'2016092500000000001','5e44e740-8f36-4a4d-9583-fea77c5b78d2'),('e314f4bc-3b5d-4c63-bd14-64de0aec251a',1,1323.00,'2016092700000000001','5e44e740-8f36-4a4d-9583-fea77c5b78d2'),('e33e6026-11df-4573-a8ff-4f36f17a1fa9',2,24.00,'2016092700000000005','9d746382-496e-4757-8e36-614385c5762c'),('e352fce3-8756-4216-b978-7c4563844b4f',1,12.00,'2016092800000000004','9d746382-496e-4757-8e36-614385c5762c'),('e4fdbbf5-4448-4f33-b725-17505f268b78',1,13.00,'2016092800000000003','935fb1ba-f18d-42f2-abc6-ccedd5379066'),('edce19ef-1b1d-4b4c-a626-b8e137580ee0',1,123.00,'2016092500000000001','19e1b370-032f-4b3f-93b7-0cc1225604c4'),('f111e151-12a4-45c0-9565-3aa741858241',1,12.00,'2016092800000000002','9d746382-496e-4757-8e36-614385c5762c');
/*!40000 ALTER TABLE `orderitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordernum`
--

DROP TABLE IF EXISTS `ordernum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordernum` (
  `prefix` date DEFAULT NULL,
  `num` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordernum`
--

LOCK TABLES `ordernum` WRITE;
/*!40000 ALTER TABLE `ordernum` DISABLE KEYS */;
INSERT INTO `ordernum` VALUES ('2016-09-25',1),('2016-09-26',13),('2016-09-27',5),('2016-09-28',5);
/*!40000 ALTER TABLE `ordernum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `ordernum` varchar(100) NOT NULL,
  `price` float(8,2) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `customerId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ordernum`),
  KEY `customerId_fk` (`customerId`),
  CONSTRAINT `customerId_fk` FOREIGN KEY (`customerId`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('2016092500000000001',1446.00,2,0,'dc85c76d-0421-446a-a7f9-de56be368d37'),('2016092600000000001',21321.00,1,0,'dc85c76d-0421-446a-a7f9-de56be368d37'),('2016092600000000002',21321.00,1,0,'a9d85286-1756-4755-aca8-f300f31e8f9d'),('2016092600000000003',21321.00,1,0,'a9d85286-1756-4755-aca8-f300f31e8f9d'),('2016092600000000004',21321.00,1,1,'a9d85286-1756-4755-aca8-f300f31e8f9d'),('2016092600000000005',21321.00,1,1,'a9d85286-1756-4755-aca8-f300f31e8f9d'),('2016092600000000006',21321.00,1,0,'a9d85286-1756-4755-aca8-f300f31e8f9d'),('2016092600000000007',21321.00,1,0,'dc85c76d-0421-446a-a7f9-de56be368d37'),('2016092600000000008',21321.00,1,0,'dc85c76d-0421-446a-a7f9-de56be368d37'),('2016092600000000009',21321.00,1,0,'a9d85286-1756-4755-aca8-f300f31e8f9d'),('2016092600000000010',21321.00,1,1,'a9d85286-1756-4755-aca8-f300f31e8f9d'),('2016092600000000011',765756.00,1,0,'dc85c76d-0421-446a-a7f9-de56be368d37'),('2016092600000000012',12.00,1,0,'dc85c76d-0421-446a-a7f9-de56be368d37'),('2016092600000000013',123.00,1,0,'dc85c76d-0421-446a-a7f9-de56be368d37'),('2016092700000000001',4656.00,4,1,'a9d85286-1756-4755-aca8-f300f31e8f9d'),('2016092700000000002',2222.00,2,1,'dc85c76d-0421-446a-a7f9-de56be368d37'),('2016092700000000005',50.00,4,1,'dc85c76d-0421-446a-a7f9-de56be368d37'),('2016092800000000001',114.00,7,1,'dc85c76d-0421-446a-a7f9-de56be368d37'),('2016092800000000002',103.00,6,0,'dc85c76d-0421-446a-a7f9-de56be368d37'),('2016092800000000003',103.00,6,0,'dc85c76d-0421-446a-a7f9-de56be368d37'),('2016092800000000004',38.00,3,1,'a9d85286-1756-4755-aca8-f300f31e8f9d'),('2016092800000000005',55.00,2,1,'a9d85286-1756-4755-aca8-f300f31e8f9d');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_function`
--

DROP TABLE IF EXISTS `role_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_function` (
  `r_id` varchar(100) NOT NULL DEFAULT '',
  `f_id` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`r_id`,`f_id`),
  KEY `function_id_fk` (`f_id`),
  CONSTRAINT `function_id_fk` FOREIGN KEY (`f_id`) REFERENCES `functions` (`id`),
  CONSTRAINT `role_id_fk1` FOREIGN KEY (`r_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_function`
--

LOCK TABLES `role_function` WRITE;
/*!40000 ALTER TABLE `role_function` DISABLE KEYS */;
INSERT INTO `role_function` VALUES ('1','1'),('2','1'),('1','2'),('2','2'),('1','3'),('1','4'),('1','5'),('2','5'),('1','6'),('2','6');
/*!40000 ALTER TABLE `role_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `des` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('1','superadmin','can access any page'),('2','admin','book section');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `u_id` varchar(100) NOT NULL DEFAULT '',
  `r_id` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`u_id`,`r_id`),
  KEY `role_id_fk2` (`r_id`),
  CONSTRAINT `role_id_fk2` FOREIGN KEY (`r_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`u_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('1','1'),('2','2');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('1','admin','admin'),('2','aa','123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-25  0:26:21
