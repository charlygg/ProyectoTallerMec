-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema proyectotallermec
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema proyectotallermec
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `proyectotallermec` DEFAULT CHARACTER SET utf8 ;
USE `proyectotallermec` ;

-- -----------------------------------------------------
-- Table `proyectotallermec`.`tbl_auto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectotallermec`.`tbl_auto` (
  `id_auto` INT NOT NULL,
  `modelo` VARCHAR(45) NOT NULL,
  `marca` VARCHAR(25) NOT NULL,
  `linea` VARCHAR(45) NOT NULL,
  `anio` INT NOT NULL,
  `color` VARCHAR(15) NOT NULL,
  `placas` VARCHAR(13) NOT NULL,
  `kilometraje` INT NOT NULL,
  `serie` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_auto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectotallermec`.`tbl_clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectotallermec`.`tbl_clientes` (
  `id_cliente` INT NOT NULL,
  `nombre_completo` VARCHAR(150) NOT NULL,
  `compania` VARCHAR(150) NULL,
  `rfc` VARCHAR(13) NULL,
  `calle` VARCHAR(150) NULL,
  `no_ext` VARCHAR(20) NULL,
  `no_int` VARCHAR(20) NULL,
  `codigo_postal` INT NULL,
  `estado` VARCHAR(80) NULL,
  `telefono` VARCHAR(50) NULL,
  `correo` VARCHAR(75) NULL,
  `activo` VARCHAR(1) NOT NULL,
  `ciudad` VARCHAR(45) NULL,
  `fax` VARCHAR(45) NULL,
  PRIMARY KEY (`id_cliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectotallermec`.`tbl_catalogo_servicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectotallermec`.`tbl_catalogo_servicios` (
  `id_catalogo_servicios` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `detalle` VARCHAR(45) NOT NULL,
  `activo` VARCHAR(1) NOT NULL,
  `precio` DECIMAL NULL,
  `tbl_catalogo_servicioscol` VARCHAR(45) NULL,
  PRIMARY KEY (`id_catalogo_servicios`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectotallermec`.`tbl_usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectotallermec`.`tbl_usuarios` (
  `id_usuarios` INT NOT NULL,
  `nombre` VARCHAR(75) NOT NULL,
  `ap_pat` VARCHAR(100) NOT NULL,
  `ap_mat` VARCHAR(100) NOT NULL,
  `correo` VARCHAR(100) NULL,
  `administrador` VARCHAR(1) NOT NULL,
  `habilitado` INT NOT NULL,
  `clave` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_usuarios`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectotallermec`.`tbl_empleados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectotallermec`.`tbl_empleados` (
  `id_empleado` INT NOT NULL,
  `nombre` VARCHAR(75) NOT NULL,
  `ap_pat` VARCHAR(100) NOT NULL,
  `ap_mat` VARCHAR(100) NOT NULL,
  `habilitado` INT NOT NULL,
  PRIMARY KEY (`id_empleado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectotallermec`.`tbl_orden_trabajo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectotallermec`.`tbl_orden_trabajo` (
  `id_orden_trabajo` INT NOT NULL,
  `folio` VARCHAR(45) NULL,
  `id_cliente` INT NULL,
  `id_auto` INT NULL,
  `fecha_registro` DATETIME NULL,
  `estado` VARCHAR(1) NULL,
  `fecha_completado` DATETIME NULL,
  `tbl_usuarios_id_usuarios` INT NOT NULL,
  `id_empleado_asignado` INT NULL,
  PRIMARY KEY (`id_orden_trabajo`, `tbl_usuarios_id_usuarios`),
  INDEX `FK_CLIENTE_idx` (`id_cliente` ASC),
  INDEX `FK_AUTO_idx` (`id_auto` ASC),
  INDEX `FK_USUARIO_idx` (`tbl_usuarios_id_usuarios` ASC),
  INDEX `FK_EMPLEADO_idx` (`id_empleado_asignado` ASC),
  CONSTRAINT `FK_CLIENTE`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `proyectotallermec`.`tbl_clientes` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_AUTO`
    FOREIGN KEY (`id_auto`)
    REFERENCES `proyectotallermec`.`tbl_auto` (`id_auto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USUARIO`
    FOREIGN KEY (`tbl_usuarios_id_usuarios`)
    REFERENCES `proyectotallermec`.`tbl_usuarios` (`id_usuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_EMPLEADO`
    FOREIGN KEY (`id_empleado_asignado`)
    REFERENCES `proyectotallermec`.`tbl_empleados` (`id_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectotallermec`.`tbl_facturas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectotallermec`.`tbl_facturas` (
  `idfactura` INT NOT NULL,
  `fecha_emision` DATETIME NOT NULL,
  `fecha_pago` DATETIME NOT NULL,
  `id_orden_trabajo` INT NOT NULL,
  PRIMARY KEY (`idfactura`),
  INDEX `FK_ORDEN_TRABAJO_FAC_idx` (`id_orden_trabajo` ASC),
  CONSTRAINT `FK_ORDEN_TRABAJO_FAC`
    FOREIGN KEY (`id_orden_trabajo`)
    REFERENCES `proyectotallermec`.`tbl_orden_trabajo` (`id_orden_trabajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectotallermec`.`tbl_orden_trabajo_detalle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectotallermec`.`tbl_orden_trabajo_detalle` (
  `id_orden_trabajo_detalle` INT NOT NULL,
  `id_orden_trabajo` INT NULL,
  `id_catalogo_servicios` INT NULL,
  PRIMARY KEY (`id_orden_trabajo_detalle`),
  INDEX `FK_ORDEN_TRABAJO_idx` (`id_orden_trabajo` ASC),
  INDEX `FK_CATALOGO_SERVICIO_idx` (`id_catalogo_servicios` ASC),
  CONSTRAINT `FK_ORDEN_TRABAJO`
    FOREIGN KEY (`id_orden_trabajo`)
    REFERENCES `proyectotallermec`.`tbl_orden_trabajo` (`id_orden_trabajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CATALOGO_SERVICIO`
    FOREIGN KEY (`id_catalogo_servicios`)
    REFERENCES `proyectotallermec`.`tbl_catalogo_servicios` (`id_catalogo_servicios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
