CREATE DATABASE  IF NOT EXISTS `proyectotallermec` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `proyectotallermec`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: proyectotallermec
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.30-MariaDB

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
-- Table structure for table `tbl_auto`
--

DROP TABLE IF EXISTS `tbl_auto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_auto` (
  `id_auto` int(11) NOT NULL,
  `modelo` int(11) DEFAULT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `placas` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_auto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_auto`
--

LOCK TABLES `tbl_auto` WRITE;
/*!40000 ALTER TABLE `tbl_auto` DISABLE KEYS */;
INSERT INTO `tbl_auto` VALUES (2,2019,'BMW','IOP-232','a12'),(3,2015,'Chevrolet','RTY-409','Rojo'),(4,2016,'S','JEL-323','Negro'),(5,2018,'BMW','IOP-232','Plateado'),(6,1999,'Volswagen','FRY-2300','Blanco'),(7,2014,'Volkwagen Vento','122-EQE','Cafe');
/*!40000 ALTER TABLE `tbl_auto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_auto_cliente`
--

DROP TABLE IF EXISTS `tbl_auto_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_auto_cliente` (
  `id_auto_cliente` int(11) NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_auto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_auto_cliente`),
  KEY `CLIENTES_FK_idx` (`id_cliente`),
  KEY `AUTOS_FK_idx` (`id_auto`),
  CONSTRAINT `AUTOS_FK` FOREIGN KEY (`id_auto`) REFERENCES `tbl_auto` (`id_auto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CLIENTES_FK` FOREIGN KEY (`id_cliente`) REFERENCES `tbl_clientes` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_auto_cliente`
--

LOCK TABLES `tbl_auto_cliente` WRITE;
/*!40000 ALTER TABLE `tbl_auto_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_auto_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_clientes`
--

DROP TABLE IF EXISTS `tbl_clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_clientes` (
  `id_cliente` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `ap_pat` varchar(45) NOT NULL,
  `ap_mat` varchar(45) NOT NULL,
  `edad` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `activo` varchar(1) NOT NULL DEFAULT 'A',
  `calle` varchar(45) DEFAULT NULL,
  `no_ext` varchar(45) DEFAULT NULL,
  `no_int` varchar(45) DEFAULT NULL,
  `cod_postal` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_clientes`
--

LOCK TABLES `tbl_clientes` WRITE;
/*!40000 ALTER TABLE `tbl_clientes` DISABLE KEYS */;
INSERT INTO `tbl_clientes` VALUES (2,'Julio Cesar','Hernández','Chávez',34,'juliocesar@hotmail.com','A','Escobedo Sur','333','Piso 5',64010,'Nuevo León','8122-1234');
/*!40000 ALTER TABLE `tbl_clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'proyectotallermec'
--

--
-- Dumping routines for database 'proyectotallermec'
--
/*!50003 DROP PROCEDURE IF EXISTS `agregar_modificar_auto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar_modificar_auto`(INOUT p_id_auto	int,
										   p_modelo		int,
                                           p_marca	varchar(255),
                                           p_placas	varchar(255),
                                           p_color	varchar(255)
                                           )
BEGIN

DECLARE p_max_id	int;

IF p_id_auto = 0 THEN
	SELECT COALESCE(MAX(id_auto),1) into p_max_id from tbl_auto;
    SET p_max_id = p_max_id + 1;    
    
    INSERT INTO tbl_auto
    (
		id_auto,
        modelo,
        marca,
        placas,
        color
    )
    values
    (
		p_max_id,
        p_modelo,
        p_marca,
        p_placas,
        p_color
    );
    
    SET p_id_auto = p_max_id;
    
    Select 'Registro guardado exitosamente' as mensaje, 0 as error from dual;

ELSEIF p_id_auto > 0 THEN

	 UPDATE tbl_auto set modelo = p_modelo, marca = p_marca, placas = p_placas, color = p_color
     WHERE id_auto = p_id_auto;

	 Select 'Registro actualizado exitosamente' as mensaje, 0 as error from dual;
    
END IF;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `agregar_modificar_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar_modificar_cliente`(INOUT p_id_cliente int, 
											  p_nombre	varchar(255), 
                                              p_ap_pat	varchar(255), 
                                              p_ap_mat	varchar(255), 
                                              p_edad	int, 
                                              p_email	varchar(255), 
                                              p_activo	varchar(1), 
                                              p_calle	varchar(255), 
                                              p_no_ext	varchar(255), 
                                              p_no_int	varchar(255), 
                                              p_cod_postal int, 
                                              p_estado	varchar(255), 
                                              p_telefono varchar(255))
BEGIN

DECLARE p_max_id	int;

IF p_id_cliente = 0 THEN
	SELECT COALESCE(MAX(id_cliente),1) into p_max_id from tbl_clientes;
    SET p_max_id = p_max_id + 1;  
    
    INSERT INTO tbl_clientes
    (
		id_cliente, 
		nombre, 
		ap_pat, 
		ap_mat, 
		edad, 
		email, 
		activo, 
		calle, 
		no_ext, 
		no_int, 
		cod_postal, 
		estado, 
		telefono
    )
    values
    (
		p_max_id,
		p_nombre, 
		p_ap_pat, 
		p_ap_mat, 
		p_edad, 
		p_email, 
		p_activo, 
		p_calle, 
		p_no_ext, 
		p_no_int, 
		p_cod_postal, 
		p_estado, 
		p_telefono 
    );
    
    SET p_id_cliente = p_max_id;
    
    Select 'Registro guardado exitosamente' as mensaje, 0 as error from dual;
    
END IF;    

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `obtener_autos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_autos`()
BEGIN

SELECT *
FROM tbl_auto;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `obtener_auto_by_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_auto_by_id`(p_id	int)
BEGIN

SELECT * from tbl_auto
where id_auto = p_id;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `obtener_cliente_by_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_cliente_by_id`(p_id	int)
BEGIN

SELECT * from tbl_clientes
where id_cliente = p_id;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-01 21:37:46
