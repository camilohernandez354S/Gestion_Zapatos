-- MySQL dump 10.13  Distrib 8.4.3, for Win64 (x86_64)
--
-- Host: localhost    Database: zapatillas
-- ------------------------------------------------------
-- Server version	8.4.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `parametros`
--

DROP TABLE IF EXISTS `parametros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parametros` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametros`
--

LOCK TABLES `parametros` WRITE;
/*!40000 ALTER TABLE `parametros` DISABLE KEYS */;
INSERT INTO `parametros` VALUES (1,'25'),(2,'26'),(3,'27'),(4,'28'),(5,'29'),(6,'30'),(7,'31'),(8,'32'),(9,'33'),(10,'34'),(11,'35'),(12,'36'),(13,'37'),(14,'38'),(15,'39'),(16,'40'),(17,'41'),(18,'42'),(19,'43'),(20,'44'),(21,'45'),(37,'ADIDAS'),(35,'ASICS'),(26,'BALONCESTO'),(23,'FEMENINO'),(27,'FÚTBOL'),(33,'GIMNASIO'),(30,'GOLF'),(22,'MASCULINO'),(28,'MICRO-FÚTBOL'),(36,'MIZUNO'),(40,'NEW BALANCE'),(38,'NIKE'),(32,'PING PONG'),(39,'PUMA'),(34,'RUNNING'),(31,'SKATEBOARDING'),(29,'TENIS'),(24,'UNISEX'),(25,'VOLEIBOL');
/*!40000 ALTER TABLE `parametros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tema`
--

DROP TABLE IF EXISTS `tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tema` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tema`
--

LOCK TABLES `tema` WRITE;
/*!40000 ALTER TABLE `tema` DISABLE KEYS */;
INSERT INTO `tema` VALUES (2,'GENERO'),(4,'MARCA'),(1,'TALLAS'),(3,'TIPO');
/*!40000 ALTER TABLE `tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tema_parametros`
--

DROP TABLE IF EXISTS `tema_parametros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tema_parametros` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_tema` int DEFAULT NULL,
  `id_parametro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_tema` (`id_tema`),
  KEY `id_parametro` (`id_parametro`),
  CONSTRAINT `tema_parametros_ibfk_1` FOREIGN KEY (`id_tema`) REFERENCES `tema` (`id`),
  CONSTRAINT `tema_parametros_ibfk_2` FOREIGN KEY (`id_parametro`) REFERENCES `parametros` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tema_parametros`
--

LOCK TABLES `tema_parametros` WRITE;
/*!40000 ALTER TABLE `tema_parametros` DISABLE KEYS */;
INSERT INTO `tema_parametros` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,1,21),(22,2,22),(23,2,23),(24,2,24),(25,3,25),(26,3,26),(27,3,27),(28,3,28),(29,3,29),(30,3,30),(31,3,31),(32,3,32),(33,3,33),(34,3,34),(35,4,35),(36,4,36),(37,4,37),(38,4,38),(39,4,39),(40,4,40);
/*!40000 ALTER TABLE `tema_parametros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zapatillas`
--

DROP TABLE IF EXISTS `zapatillas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zapatillas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_talla` int DEFAULT NULL,
  `id_genero` int DEFAULT NULL,
  `id_tipo` int DEFAULT NULL,
  `id_marca` int DEFAULT NULL,
  `Foto` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_talla` (`id_talla`),
  KEY `id_genero` (`id_genero`),
  KEY `id_tipo` (`id_tipo`),
  KEY `id_marca` (`id_marca`),
  CONSTRAINT `zapatillas_ibfk_1` FOREIGN KEY (`id_talla`) REFERENCES `tema_parametros` (`id`),
  CONSTRAINT `zapatillas_ibfk_2` FOREIGN KEY (`id_genero`) REFERENCES `tema_parametros` (`id`),
  CONSTRAINT `zapatillas_ibfk_3` FOREIGN KEY (`id_tipo`) REFERENCES `tema_parametros` (`id`),
  CONSTRAINT `zapatillas_ibfk_4` FOREIGN KEY (`id_marca`) REFERENCES `tema_parametros` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zapatillas`
--

LOCK TABLES `zapatillas` WRITE;
/*!40000 ALTER TABLE `zapatillas` DISABLE KEYS */;
INSERT INTO `zapatillas` VALUES (33,NULL,11,24,25,35,'C:\\Users\\Kevin Prada\\PROYECTOS SENA\\Gestion_Zapatos\\data\\Imagenes\\ASICS\\U-ASICS-VOLEIBOL.jpeg'),(34,NULL,13,22,26,37,'C:\\Users\\Kevin Prada\\PROYECTOS SENA\\Gestion_Zapatos\\data\\Imagenes\\ADIDAS\\M-ADIDAS-BALONCESTO.jpeg'),(35,NULL,5,23,27,37,'C:\\Users\\Kevin Prada\\PROYECTOS SENA\\Gestion_Zapatos\\data\\Imagenes\\ADIDAS\\F-ADIDAS-FÚTBOL.jpg'),(36,NULL,16,22,32,38,'C:\\Users\\Kevin Prada\\PROYECTOS SENA\\Gestion_Zapatos\\data\\Imagenes\\NIKE\\M-NIKE-PING PONG.jpg'),(37,NULL,15,22,34,40,'C:\\Users\\Kevin Prada\\PROYECTOS SENA\\Gestion_Zapatos\\data\\Imagenes\\NEW BALANCE\\M-NEW BALANCE-RUNNING.jpeg'),(38,NULL,19,24,25,35,'C:\\Users\\Kevin Prada\\PROYECTOS SENA\\Gestion_Zapatos\\data\\Imagenes\\ASICS\\F-ASICS-FÚTBOL2.jpeg');
/*!40000 ALTER TABLE `zapatillas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-24 10:03:19
