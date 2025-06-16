-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.4.3 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para zapatillas
CREATE DATABASE IF NOT EXISTS `zapatillas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `zapatillas`;

-- Volcando estructura para tabla zapatillas.parametros
CREATE TABLE IF NOT EXISTS `parametros` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla zapatillas.tema
CREATE TABLE IF NOT EXISTS `tema` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla zapatillas.tema_parametros
CREATE TABLE IF NOT EXISTS `tema_parametros` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_tema` int DEFAULT NULL,
  `id_parametro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_tema` (`id_tema`),
  KEY `id_parametro` (`id_parametro`),
  CONSTRAINT `tema_parametros_ibfk_1` FOREIGN KEY (`id_tema`) REFERENCES `tema` (`id`),
  CONSTRAINT `tema_parametros_ibfk_2` FOREIGN KEY (`id_parametro`) REFERENCES `parametros` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla zapatillas.zapatillas
CREATE TABLE IF NOT EXISTS `zapatillas` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
