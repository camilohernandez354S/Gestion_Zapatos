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
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametros`
--

LOCK TABLES `parametros` WRITE;
/*!40000 ALTER TABLE `parametros` DISABLE KEYS */;
INSERT INTO `parametros` VALUES (11,'35'),(12,'36'),(13,'37'),(14,'38'),(15,'39'),(16,'40'),(17,'41'),(18,'42'),(19,'43'),(31,'ADIDAS'),(4,'AMARILLO'),(29,'ASICS'),(1,'AZUL'),(24,'BASKETBALL'),(10,'BLANCO'),(8,'CAFÉ'),(21,'FEMENINO'),(25,'FUTBOL'),(28,'GIMNASIO'),(20,'MASCULINO'),(26,'MICRO-FUTBOL'),(30,'MIZUNO'),(6,'MORADO'),(5,'NARANJA'),(9,'NEGRO'),(35,'NEW BALANCE'),(32,'NIKE'),(34,'PUMA'),(36,'REEBOK'),(2,'ROJO'),(7,'ROSADO'),(27,'RUNNING'),(33,'UNDER-ARMOUR'),(22,'UNISEX'),(3,'VERDE'),(23,'VOLEIBOL');
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
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tema`
--

LOCK TABLES `tema` WRITE;
/*!40000 ALTER TABLE `tema` DISABLE KEYS */;
INSERT INTO `tema` VALUES (1,'COLORES'),(3,'GÉNEROS'),(5,'MARCAS'),(2,'TALLAS'),(4,'TIPOS');
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tema_parametros`
--

LOCK TABLES `tema_parametros` WRITE;
/*!40000 ALTER TABLE `tema_parametros` DISABLE KEYS */;
INSERT INTO `tema_parametros` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,2,11),(12,2,12),(13,2,13),(14,2,14),(15,2,15),(16,2,16),(17,2,17),(18,2,18),(19,2,19),(20,3,20),(21,3,21),(22,3,22),(23,4,23),(24,4,24),(25,4,25),(26,4,26),(27,4,27),(28,4,28),(29,5,29),(30,5,30),(31,5,31),(32,5,32),(33,5,33),(34,5,34),(35,5,35),(36,5,36);
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
  `id_color` int DEFAULT NULL,
  `id_talla` int DEFAULT NULL,
  `id_genero` int DEFAULT NULL,
  `id_tipo` int DEFAULT NULL,
  `id_marca` int DEFAULT NULL,
  `Foto` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_color` (`id_color`),
  KEY `id_talla` (`id_talla`),
  KEY `id_genero` (`id_genero`),
  KEY `id_tipo` (`id_tipo`),
  KEY `id_marca` (`id_marca`),
  CONSTRAINT `zapatillas_ibfk_1` FOREIGN KEY (`id_color`) REFERENCES `tema_parametros` (`id`),
  CONSTRAINT `zapatillas_ibfk_2` FOREIGN KEY (`id_talla`) REFERENCES `tema_parametros` (`id`),
  CONSTRAINT `zapatillas_ibfk_3` FOREIGN KEY (`id_genero`) REFERENCES `tema_parametros` (`id`),
  CONSTRAINT `zapatillas_ibfk_4` FOREIGN KEY (`id_tipo`) REFERENCES `tema_parametros` (`id`),
  CONSTRAINT `zapatillas_ibfk_5` FOREIGN KEY (`id_marca`) REFERENCES `tema_parametros` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zapatillas`
--

LOCK TABLES `zapatillas` WRITE;
/*!40000 ALTER TABLE `zapatillas` DISABLE KEYS */;
INSERT INTO `zapatillas` VALUES (19,8,11,22,28,36,'');
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

-- Dump completed on 2025-06-20 12:19:12
