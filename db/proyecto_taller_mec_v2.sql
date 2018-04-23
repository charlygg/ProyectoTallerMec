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
  `modelo` varchar(45) NOT NULL,
  `marca` varchar(25) NOT NULL,
  `linea` varchar(45) NOT NULL,
  `anio` int(11) NOT NULL,
  `color` varchar(15) NOT NULL,
  `placas` varchar(13) NOT NULL,
  `kilometraje` int(11) NOT NULL,
  `serie` varchar(45) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  PRIMARY KEY (`id_auto`),
  KEY `FK_CLIENTE_AUTO_idx` (`id_cliente`),
  CONSTRAINT `FK_CLIENTE_AUTO` FOREIGN KEY (`id_cliente`) REFERENCES `tbl_clientes` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_auto`
--

LOCK TABLES `tbl_auto` WRITE;
/*!40000 ALTER TABLE `tbl_auto` DISABLE KEYS */;
INSERT INTO `tbl_auto` VALUES (2,'Chico','Sedan','Nissan',2015,'Rojo','ZDR-24-34',30000,'1929D-W',-1),(3,'Chico','Sedan','Nissan',2015,'Rojo','ZDR-24-34',30000,'1929D-W',2),(4,'Grande','Sedan','Nissan',2020,'Rojo','123-1D2',30000,'1929D-W',3),(5,'Grande','Sedan','Nissan',2018,'Rojo','ZDR-24-34',30000,'1929D-W',4),(6,'Grande','Sedan','Nissan',2018,'Rojo','ZDR-24-34',30000,'1929D-W',3),(7,'Pequeño','Camioneta','Nissan',2018,'Verde','ZDR-24-34',30000,'1929D-W',5),(8,'SENTRA','MARCH','Nissan',2018,'Turquesa','ABC-123',8909,'1929D-W',5);
/*!40000 ALTER TABLE `tbl_auto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_catalogo_servicios`
--

DROP TABLE IF EXISTS `tbl_catalogo_servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_catalogo_servicios` (
  `id_catalogo_servicios` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `detalle` varchar(45) NOT NULL,
  `activo` varchar(1) NOT NULL,
  `precio` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id_catalogo_servicios`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_catalogo_servicios`
--

LOCK TABLES `tbl_catalogo_servicios` WRITE;
/*!40000 ALTER TABLE `tbl_catalogo_servicios` DISABLE KEYS */;
INSERT INTO `tbl_catalogo_servicios` VALUES (1,'Lavado de Motor','Se aplica una revisión minuciosa al motor y s','Y',2500),(2,'Ajuste de la velocidad y mezcla de ralentí','','1',450),(3,'Limpieza de carburador','','1',500);
/*!40000 ALTER TABLE `tbl_catalogo_servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_clientes`
--

DROP TABLE IF EXISTS `tbl_clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_clientes` (
  `id_cliente` int(11) NOT NULL,
  `nombre_completo` varchar(150) NOT NULL,
  `compania` varchar(150) DEFAULT NULL,
  `rfc` varchar(13) DEFAULT NULL,
  `calle` varchar(150) DEFAULT NULL,
  `no_ext` varchar(20) DEFAULT NULL,
  `no_int` varchar(20) DEFAULT NULL,
  `codigo_postal` int(11) DEFAULT NULL,
  `estado` varchar(80) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `correo` varchar(75) DEFAULT NULL,
  `activo` varchar(1) NOT NULL,
  `ciudad` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_clientes`
--

LOCK TABLES `tbl_clientes` WRITE;
/*!40000 ALTER TABLE `tbl_clientes` DISABLE KEYS */;
INSERT INTO `tbl_clientes` VALUES (-1,'SIN ASIGNAR','','','','','',NULL,'','','','0','',''),(2,'Héctor G. H.','H3 SEGUROS','ABDBD1I2IE2','ESCOBEDO SUR','123','A',65789,'NUEVO LEON','8393-9990','c@H3.com','1','SALTILLO','123-34'),(3,'PABLO ESCOBAR','ABBA SEGUROS','PAB1234XYZ','CALLE','OPO','032',67888,'NUEVO SUR','23923','2323','1','CALLE',''),(4,'LAURA GARZA','AAA','LAU123','','','',0,'','','','1','',NULL),(5,'JORGE VIA','NA','JORGE123OPI','','','',0,'','','','1','MARIANO MATAMOROS','123-980');
/*!40000 ALTER TABLE `tbl_clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_empleados`
--

DROP TABLE IF EXISTS `tbl_empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_empleados` (
  `id_empleado` int(11) NOT NULL,
  `nombre` varchar(75) NOT NULL,
  `ap_pat` varchar(100) NOT NULL,
  `ap_mat` varchar(100) NOT NULL,
  `habilitado` int(11) NOT NULL,
  PRIMARY KEY (`id_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_empleados`
--

LOCK TABLES `tbl_empleados` WRITE;
/*!40000 ALTER TABLE `tbl_empleados` DISABLE KEYS */;
INSERT INTO `tbl_empleados` VALUES (1,'Francisco','Guillermo','Gómez',1);
/*!40000 ALTER TABLE `tbl_empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_facturas`
--

DROP TABLE IF EXISTS `tbl_facturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_facturas` (
  `idfactura` int(11) NOT NULL,
  `fecha_emision` datetime NOT NULL,
  `fecha_pago` datetime NOT NULL,
  `id_orden_trabajo` int(11) NOT NULL,
  PRIMARY KEY (`idfactura`),
  KEY `FK_ORDEN_TRABAJO_FAC_idx` (`id_orden_trabajo`),
  CONSTRAINT `FK_ORDEN_TRABAJO_FAC` FOREIGN KEY (`id_orden_trabajo`) REFERENCES `tbl_orden_trabajo` (`id_orden_trabajo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_facturas`
--

LOCK TABLES `tbl_facturas` WRITE;
/*!40000 ALTER TABLE `tbl_facturas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_facturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_orden_trabajo`
--

DROP TABLE IF EXISTS `tbl_orden_trabajo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_orden_trabajo` (
  `id_orden_trabajo` int(11) NOT NULL,
  `folio` varchar(45) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_auto` int(11) DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `estado` varchar(1) DEFAULT NULL,
  `fecha_completado` datetime DEFAULT NULL,
  `tbl_usuarios_id_usuarios` int(11) NOT NULL,
  `id_empleado_asignado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_orden_trabajo`,`tbl_usuarios_id_usuarios`),
  KEY `FK_CLIENTE_idx` (`id_cliente`),
  KEY `FK_AUTO_idx` (`id_auto`),
  KEY `FK_USUARIO_idx` (`tbl_usuarios_id_usuarios`),
  KEY `FK_EMPLEADO_idx` (`id_empleado_asignado`),
  CONSTRAINT `FK_AUTO` FOREIGN KEY (`id_auto`) REFERENCES `tbl_auto` (`id_auto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CLIENTE` FOREIGN KEY (`id_cliente`) REFERENCES `tbl_clientes` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_EMPLEADO` FOREIGN KEY (`id_empleado_asignado`) REFERENCES `tbl_empleados` (`id_empleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USUARIO` FOREIGN KEY (`tbl_usuarios_id_usuarios`) REFERENCES `tbl_usuarios` (`id_usuarios`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_orden_trabajo`
--

LOCK TABLES `tbl_orden_trabajo` WRITE;
/*!40000 ALTER TABLE `tbl_orden_trabajo` DISABLE KEYS */;
INSERT INTO `tbl_orden_trabajo` VALUES (2,'F',2,2,'2008-07-04 12:23:00','A',NULL,1,1);
/*!40000 ALTER TABLE `tbl_orden_trabajo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_orden_trabajo_detalle`
--

DROP TABLE IF EXISTS `tbl_orden_trabajo_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_orden_trabajo_detalle` (
  `id_orden_trabajo_detalle` int(11) NOT NULL,
  `id_orden_trabajo` int(11) DEFAULT NULL,
  `id_catalogo_servicios` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_orden_trabajo_detalle`),
  KEY `FK_ORDEN_TRABAJO_idx` (`id_orden_trabajo`),
  KEY `FK_CATALOGO_SERVICIO_idx` (`id_catalogo_servicios`),
  CONSTRAINT `FK_CATALOGO_SERVICIO` FOREIGN KEY (`id_catalogo_servicios`) REFERENCES `tbl_catalogo_servicios` (`id_catalogo_servicios`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ORDEN_TRABAJO` FOREIGN KEY (`id_orden_trabajo`) REFERENCES `tbl_orden_trabajo` (`id_orden_trabajo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_orden_trabajo_detalle`
--

LOCK TABLES `tbl_orden_trabajo_detalle` WRITE;
/*!40000 ALTER TABLE `tbl_orden_trabajo_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_orden_trabajo_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usuarios`
--

DROP TABLE IF EXISTS `tbl_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_usuarios` (
  `id_usuarios` int(11) NOT NULL,
  `nombre` varchar(75) NOT NULL,
  `nick` varchar(100) NOT NULL,
  `ap_pat` varchar(100) NOT NULL,
  `ap_mat` varchar(100) NOT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `administrador` varchar(1) NOT NULL,
  `habilitado` int(11) NOT NULL,
  `clave` varchar(100) NOT NULL,
  PRIMARY KEY (`id_usuarios`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuarios`
--

LOCK TABLES `tbl_usuarios` WRITE;
/*!40000 ALTER TABLE `tbl_usuarios` DISABLE KEYS */;
INSERT INTO `tbl_usuarios` VALUES (1,'Administrador','admin','','','admin@gmail.com','1',1,'123');
/*!40000 ALTER TABLE `tbl_usuarios` ENABLE KEYS */;
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
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar_modificar_auto`(INOUT p_id_auto	int,
										                       IN p_modelo		varchar(45),
                                           IN p_marca	varchar(255),
                                           IN p_linea  varchar(45),
                                           IN p_anio   int,
                                           IN p_serie	varchar(255),
                                           IN p_color	varchar(255),
                                           IN p_placas varchar(13),
                                           IN p_kilometraje  INT,
                                           IN p_id_cliente  INT
                                           )
BEGIN

DECLARE p_max_id	int;
DECLARE p_existe  int;

IF p_id_auto = 0 THEN
	SELECT COALESCE(MAX(id_auto),1) into p_max_id from tbl_auto;
    SET p_max_id = p_max_id + 1;    
    
    INSERT INTO tbl_auto
    (
      id_auto,
      modelo,
      marca,
      linea,
      anio,
      color,
      placas,
      kilometraje,
      serie,
      id_cliente
    )
    values
    (
      p_max_id,
      p_modelo,
      p_marca,
      p_linea,
      p_anio,
      p_color,
      p_placas,
      p_kilometraje ,
      p_serie,
       COALESCE(IF(p_id_cliente=0,-1,p_id_cliente),-1) -- Si se asigna cliente por defecto si no se elige un cliente existente
    );
    
    SET p_id_auto = p_max_id;
    
    Select 'Registro guardado exitosamente' as mensaje, 0 as error, p_id_auto as p_id_auto from dual;

ELSEIF p_id_auto > 0 THEN

    SELECT COUNT(*) INTO p_existe
    FROM tbl_auto WHERE id_auto = p_id_auto;

    IF p_existe > 0 THEN
    
      UPDATE tbl_auto set modelo	=	p_modelo
          ,marca	=	p_marca
          ,linea	=	p_linea  
          ,anio	=	p_anio   
          ,color	=	p_color
          ,placas	=	p_placas 
          ,kilometraje	=	p_kilometraje 
          ,serie	=	p_serie
          ,id_cliente = COALESCE(IF(p_id_cliente=0,-1,p_id_cliente),-1) -- Si se asigna cliente por defecto si no se elige un cliente existente
      WHERE id_auto = p_id_auto;      
      
      Select 'Registro actualizado exitosamente' as mensaje, 0 as error, 1 as actualizado  from dual;
    ELSEIF p_existe = 0 THEN
      Select 'El auto no existe para actualizar, verificar que el id exista en la tabla de autos' as mensaje, 1 as error, p_max_id as p_id_cliente  from dual;
    END IF;
    
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
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar_modificar_cliente`(INOUT p_id_cliente int, 
											                        IN p_nombre_comp	varchar(255), 
                                              IN p_compania	varchar(255), 
                                              IN p_rfc	varchar(255), 
                                              IN p_calle varchar(150),
                                              IN p_no_ext	varchar(255), 
                                              IN p_no_int	varchar(255), 
                                              IN p_cod_postal int, 
                                              IN p_estado	varchar(255), 
                                              IN p_telefono varchar(255),
                                              IN p_email	varchar(255), 
                                              IN p_activo	varchar(1), 
                                              IN p_ciudad  varchar(45),
                                              IN p_fax varchar(45)
                                              )
BEGIN

DECLARE p_max_id	int;
DECLARE p_existe  int;

IF p_id_cliente = 0 THEN
	SELECT COALESCE(MAX(id_cliente),1) into p_max_id from tbl_clientes;
    SET p_max_id = p_max_id + 1;  
    
    INSERT INTO tbl_clientes
    (
    	id_cliente,
    	nombre_completo,
    	compania,
    	rfc,
    	calle,
    	no_ext,
    	no_int,
    	codigo_postal	,
    	estado,
    	telefono,
    	correo,
    	activo,
    	ciudad,
    	fax
    )
    values
    (
  		p_max_id,
      p_nombre_comp	, 
      p_compania, 
      p_rfc	, 
      p_calle,
      p_no_ext,
      p_no_int,
      p_cod_postal,
      p_estado,
      p_telefono,
      p_email,
      p_activo,
      p_ciudad,
      p_fax
    );
    
    SET p_id_cliente = p_max_id;
    
    Select 'Registro guardado exitosamente' as mensaje, 0 as error, p_max_id as p_id_cliente  from dual;

ELSEIF p_id_cliente > 0 THEN   

    SELECT COUNT(*) INTO p_existe
    FROM tbl_clientes WHERE id_cliente = p_id_cliente;
    
    IF p_existe > 0 THEN
    
      UPDATE tbl_clientes SET nombre_completo	=	p_nombre_comp,
            compania	=	p_compania,
            rfc	=	p_rfc,
            calle	=	p_calle,
            no_ext	=	p_no_ext,
            no_int	=	p_no_int,
            codigo_postal	=	p_cod_postal,
            estado	=	p_estado,
            telefono	=	p_telefono,
            correo	=	p_email,
            activo	=	p_activo,
            ciudad	=	p_ciudad,
            fax	=	p_fax
      WHERE id_cliente = p_id_cliente;
    
      Select 'Registro actualizado exitosamente' as mensaje, 0 as error, 1 as actualizado  from dual;
    ELSEIF p_existe = 0 THEN
      Select 'El registro no existe para actualizar, verificar que el id exista en la tabla' as mensaje, 1 as error, p_max_id as p_id_cliente  from dual;
    END IF;

    
END IF;    

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `agregar_modificar_orden_trabajo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar_modificar_orden_trabajo`(INOUT p_id_orden_trabajo	int,
                                                   IN p_folio varchar(45),
                                                   IN p_id_cliente  INT,
                                                   IN p_id_auto INT,
                                                   IN p_fecha_registro  DATETIME,
                                                   IN p_estado  VARCHAR(1),
                                                   IN p_fecha_completado  DATETIME,
                                                   IN p_id_usuario  INT,
                                                   IN p_id_empleado INT
                                                   )
BEGIN
	
DECLARE p_max_id	int;
DECLARE p_existe  int;  

IF p_id_orden_trabajo = 0 THEN
    SELECT COALESCE(MAX(id_orden_trabajo),1) into p_max_id from tbl_orden_trabajo;
    SET p_max_id = p_max_id + 1;  
    
    INSERT INTO tbl_orden_trabajo 
    (
      id_orden_trabajo,
      folio,
      id_cliente,
      id_auto,
      fecha_registro,
      estado,
      fecha_completado,
      tbl_usuarios_id_usuarios,
      id_empleado_asignado
    ) VALUES
    (
      p_max_id,
      p_folio,
      p_id_cliente,
      p_id_auto,
      p_fecha_registro,
      p_estado,
      p_fecha_completado,
      p_id_usuario,
      p_id_empleado
    );
    
    SET p_id_orden_trabajo = p_max_id;
    
    Select 'Registro guardado exitosamente' as mensaje, 0 as error, p_max_id as p_id_orden_trabajo  from dual;

ELSEIF p_id_orden_trabajo > 0 THEN
  
    SELECT COUNT(*) INTO p_existe
    FROM tbl_orden_trabajo WHERE id_orden_trabajo = p_id_orden_trabajo;
    
    IF p_existe > 0 THEN
    
      UPDATE tbl_orden_trabajo SET folio	=	p_folio
        ,id_cliente	=	p_id_cliente
        ,id_auto	=	p_id_auto 
        ,fecha_registro	=	p_fecha_registro  
        ,estado	=	p_estado  
        ,fecha_completado	=	p_fecha_completado
        ,tbl_usuarios_id_usuarios	=	p_id_usuario
        ,id_empleado_asignado	=	p_id_empleado      
      WHERE id_orden_trabajo = p_id_orden_trabajo;
    
      Select 'Registro actualizado exitosamente' as mensaje, 0 as error, 1 as actualizado  from dual;
    ELSEIF p_existe = 0 THEN
      Select 'El registro no existe para actualizar, verificar que el id exista en la tabla' as mensaje, 1 as error, p_max_id as p_id_cliente  from dual;
    END IF;
  
END IF;
  
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `obtener_autos_from_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_autos_from_cliente`(in p_id_cliente INT)
BEGIN
-- Si es cliente "-2" traer todos los automoviles
IF p_id_cliente = -2 then

SELECT * 
from tbl_auto;

ELSE

SELECT * 
from tbl_auto where id_cliente = p_id_cliente;


END IF;

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
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_cliente_by_id`(p_id	int)
BEGIN
-- Si es cliente "-2" traer todos los clientes
IF p_id = -2 then
SELECT * from tbl_clientes where id_cliente <> -1;
ELSE
SELECT * from tbl_clientes
where id_cliente = p_id;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `obtener_orden_trabajo_by_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_orden_trabajo_by_id`(IN  p_id_orden_trabajo  INT)
BEGIN

IF p_id_orden_trabajo = -2 THEN
  select * from tbl_orden_trabajo;
ELSE
  select * from tbl_orden_trabajo
  where id_orden_trabajo = p_id_orden_trabajo;
END IF;
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

-- Dump completed on 2018-04-22 22:26:25
