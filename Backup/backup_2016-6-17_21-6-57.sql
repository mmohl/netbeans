-- MySQL dump 10.16  Distrib 10.1.9-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: pvl_tb_kel
-- ------------------------------------------------------
-- Server version	10.1.9-MariaDB

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
-- Current Database: `pvl_tb_kel`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `pvl_tb_kel` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `pvl_tb_kel`;

--
-- Table structure for table `detail_pesanan`
--

DROP TABLE IF EXISTS `detail_pesanan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail_pesanan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_pesanan` int(10) unsigned NOT NULL,
  `uang_muka` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pesanan` (`id_pesanan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_pesanan`
--

LOCK TABLES `detail_pesanan` WRITE;
/*!40000 ALTER TABLE `detail_pesanan` DISABLE KEYS */;
/*!40000 ALTER TABLE `detail_pesanan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kas`
--

DROP TABLE IF EXISTS `kas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kas` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_pesanan` int(10) unsigned NOT NULL,
  `tanggal` date NOT NULL,
  `nominal` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kas`
--

LOCK TABLES `kas` WRITE;
/*!40000 ALTER TABLE `kas` DISABLE KEYS */;
INSERT INTO `kas` VALUES (1,5,'2016-06-17',900),(2,6,'2016-06-17',900),(3,7,'2016-06-17',900);
/*!40000 ALTER TABLE `kas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategori`
--

DROP TABLE IF EXISTS `kategori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kategori` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kategori` varchar(20) NOT NULL,
  `harga` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategori`
--

LOCK TABLES `kategori` WRITE;
/*!40000 ALTER TABLE `kategori` DISABLE KEYS */;
/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `konsumen`
--

DROP TABLE IF EXISTS `konsumen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `konsumen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ktp` char(16) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jenis_kelamin` char(1) NOT NULL,
  `no_handphone` varchar(12) NOT NULL,
  `alamat` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_ktp` (`ktp`),
  KEY `index_nama` (`nama`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `konsumen`
--

LOCK TABLES `konsumen` WRITE;
/*!40000 ALTER TABLE `konsumen` DISABLE KEYS */;
INSERT INTO `konsumen` VALUES (1,'1111111111111111','Maulana','p','08997226285','Banjaran Indah'),(2,'1111111111111112','Bobi','w','08997226285','Banjaran Indah'),(3,'1111111111111113','Pani','p','08997226285','Banjaran Indah'),(4,'1111111111111114','Jaka','p','08997226285','Banjaran Indah'),(5,'1111111111111115','Podi','p','08997226285','Banjaran Indah');
/*!40000 ALTER TABLE `konsumen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasangan`
--

DROP TABLE IF EXISTS `pasangan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pasangan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_pegawai` int(10) unsigned NOT NULL,
  `id_kategori` varchar(50) NOT NULL,
  `harga` smallint(5) unsigned NOT NULL,
  `status` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_kategori` (`id_kategori`),
  KEY `fk_pegawai` (`id_pegawai`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasangan`
--

LOCK TABLES `pasangan` WRITE;
/*!40000 ALTER TABLE `pasangan` DISABLE KEYS */;
INSERT INTO `pasangan` VALUES (1,1,'2-3',50000,'1'),(2,2,'2-3',50000,'1'),(3,3,'2-3',50000,'1'),(4,4,'2-3',50000,'1');
/*!40000 ALTER TABLE `pasangan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pegawai`
--

DROP TABLE IF EXISTS `pegawai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pegawai` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `jenis_kelamin` char(1) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `ktp` char(16) NOT NULL,
  `no_handphone` varchar(12) NOT NULL,
  `gambar` varchar(50) DEFAULT NULL,
  `status` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_ktp` (`ktp`),
  KEY `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pegawai`
--

LOCK TABLES `pegawai` WRITE;
/*!40000 ALTER TABLE `pegawai` DISABLE KEYS */;
INSERT INTO `pegawai` VALUES (1,'Maulana','p','1990-01-01','1111111111111119','08997226285',NULL,'1'),(2,'Mohammad','p','1991-01-01','1111111111111109','08997226285',NULL,'1'),(3,'Kodir','p','1992-01-01','1111111111111199','08997226285',NULL,'1'),(4,'Madin','p','1993-01-01','1111111111111189','08997226285',NULL,'1'),(5,'Maryadi','p','1994-01-01','1111111111111179','08997226285',NULL,'1'),(6,'Madam','p','1990-01-01','1111111111111169','08997226285',NULL,'1'),(7,'Mapo','p','1991-01-01','1111111111111159','08997226285',NULL,'1'),(8,'Mas','p','1992-01-01','1111111111111149','08997226285',NULL,'1'),(9,'Max','p','1993-01-01','1111111111111139','08997226285',NULL,'1'),(10,'Moki','p','1994-01-01','1111111111111129','08997226285',NULL,'1'),(11,'Maya','w','1990-01-01','2111111111111119','08997226285',NULL,'0'),(12,'Mina','w','1991-01-01','2111111111111109','08997226285',NULL,'0'),(13,'Kodar','p','1992-01-01','2111111111111199','08997226285',NULL,'0');
/*!40000 ALTER TABLE `pegawai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pesanan`
--

DROP TABLE IF EXISTS `pesanan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pesanan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_pasangan` int(10) unsigned NOT NULL,
  `id_konsumen` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `lama` tinyint(3) unsigned NOT NULL,
  `status` char(1) NOT NULL,
  `total` int(10) unsigned NOT NULL,
  `pembayaran` char(1) NOT NULL,
  `nomor_pesanan` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pembayaran` (`pembayaran`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pesanan`
--

LOCK TABLES `pesanan` WRITE;
/*!40000 ALTER TABLE `pesanan` DISABLE KEYS */;
INSERT INTO `pesanan` VALUES (1,1,1,'2016-01-01',2,'1',3000,'0','PP/2/16101/55441'),(2,2,1,'2016-01-02',2,'1',3000,'0','PP/2/16102/55441'),(3,1,2,'2016-01-03',2,'1',3000,'0','PP/2/16103/55441'),(4,4,4,'2016-01-04',2,'1',3000,'0','PP/2/16104/55441'),(5,1,2,'2016-01-05',2,'1',3000,'0','PP/2/16105/55441'),(6,1,1,'2016-01-06',2,'1',3000,'0','PP/2/16106/55441'),(7,1,3,'2016-01-07',2,'1',3000,'0','PP/2/16107/55441'),(8,4,3,'2016-02-01',2,'0',3000,'0','PP/2/16108/55441');
/*!40000 ALTER TABLE `pesanan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saldo`
--

DROP TABLE IF EXISTS `saldo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `saldo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_pesanan` int(10) unsigned NOT NULL,
  `id_pegawai` int(10) unsigned NOT NULL,
  `tanggal` date NOT NULL,
  `nominal` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saldo`
--

LOCK TABLES `saldo` WRITE;
/*!40000 ALTER TABLE `saldo` DISABLE KEYS */;
INSERT INTO `saldo` VALUES (1,5,1,'2016-06-17',2100),(2,6,1,'2016-06-17',2100),(3,7,1,'2016-06-17',2100);
/*!40000 ALTER TABLE `saldo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Fulton','fapfap','0'),(2,'Adam','green','1'),(4,'Garrison','red','0'),(5,'Zachery','red','1'),(6,'Stephen','green','1'),(7,'Ryan','violet','0'),(8,'Logan','blue','0'),(9,'Tanner','violet','1'),(10,'Timon','red','0'),(11,'Jonas','indigo','0'),(12,'Harlan','orange','0'),(13,'Mannix','blue','0'),(14,'Allistair','yellow','1'),(15,'Deacon','indigo','1'),(16,'Russell','red','0'),(17,'Hall','violet','1'),(18,'Scott','yellow','1'),(19,'Rogan','indigo','0'),(20,'Holmes','indigo','0'),(21,'Andrew','yellow','0'),(22,'Lewis','violet','1'),(23,'Barrett','green','1'),(24,'Aidan','yellow','0'),(25,'Cullen','yellow','1'),(26,'Jin','green','1'),(27,'Cruz','blue','1'),(28,'Hamish','indigo','0'),(29,'Thor','blue','1'),(30,'Nero','red','1'),(31,'Micah','indigo','0'),(32,'Josiah','orange','1'),(33,'Grant','yellow','0'),(34,'Lee','blue','1'),(35,'Akeem','yellow','1'),(36,'Peter','yellow','0'),(37,'Thane','red','0'),(38,'Theodore','orange','1'),(39,'Harrison','blue','1'),(40,'Herrod','violet','1'),(41,'Chandler','red','1'),(42,'Micah','blue','0'),(43,'Leroy','orange','1'),(44,'Yasir','indigo','0'),(45,'Judah','green','0'),(46,'Zahir','orange','1'),(47,'Martin','red','0'),(48,'Carter','green','1'),(49,'Forrest','indigo','1'),(50,'Reuben','yellow','0'),(51,'Aidan','violet','1'),(52,'Chancellor','orange','1'),(53,'Walter','blue','0'),(54,'Nathaniel','indigo','1'),(55,'Abdul','red','0'),(56,'Wing','yellow','1'),(57,'Griffin','red','1'),(58,'Bert','violet','1'),(59,'Rashad','indigo','0'),(60,'Jameson','orange','1'),(61,'Raymond','violet','1'),(62,'Stone','green','0'),(63,'Yasir','yellow','1'),(64,'Jonas','indigo','0'),(65,'Caleb','yellow','0'),(66,'Eaton','green','1'),(67,'Wallace','blue','0'),(68,'Declan','indigo','0'),(69,'Channing','orange','0'),(70,'Steel','blue','1'),(71,'Lester','blue','1'),(72,'Lars','green','1'),(73,'Price','violet','0'),(74,'Amir','orange','1'),(75,'Cade','blue','1'),(76,'Upton','indigo','1'),(77,'Buckminster','violet','0'),(78,'Holmes','violet','1'),(79,'Vance','orange','0'),(80,'Rajah','orange','1'),(81,'Aristotle','blue','0'),(82,'Paul','green','0'),(83,'Erich','violet','1'),(84,'Harding','blue','0'),(85,'Damon','orange','1'),(86,'Kamal','blue','1'),(87,'Wyatt','red','1'),(88,'Keith','blue','1'),(89,'Trevor','violet','1'),(90,'Raymond','red','1'),(91,'Todd','yellow','1'),(92,'Noah','blue','1'),(93,'Tyler','violet','1'),(94,'Nero','yellow','0'),(95,'Tate','green','1'),(96,'Damian','red','1'),(97,'Troy','blue','1'),(98,'Lyle','green','1'),(99,'Keaton','red','0'),(100,'Zephania','blue','0'),(101,'kolot','kolot','0'),(102,'a','a','0');
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

-- Dump completed on 2016-06-17 21:06:57
