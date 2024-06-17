-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `LojaDeDoces` DEFAULT CHARACTER SET utf8 ;
DROP DATABASE IF EXISTS LojaDeDoces;
CREATE DATABASE LojaDeDoces;

use LojaDeDoces;
USE `LojaDeDoces`;

DROP TABLE Receitas;
DROP TABLE Doces;
DROP TABLE clientes;
DROP TABLE clientes_has_pedidos;
DROP TABLE Endereços;
DROP TABLE Pedidos;
DROP TABLE Pedidos_has_doces;

-- -----------------------------------------------------
-- Table `mydb`.`Receitas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LojaDeDoces`.`Receitas` (
  `NomeReceita` VARCHAR(15) NOT NULL,
  `Ovo` TINYINT NULL,
  `Farinha` TINYINT NULL,
  `Fermento` TINYINT NULL,
  `Leite` TINYINT NULL,
  `Acucar` TINYINT NULL,
  `Cacau` TINYINT NULL,
  `Cenoura` TINYINT NULL,
  `Morango` TINYINT NULL,
  `Abacaxi` TINYINT NULL,
  `Chantilly` TINYINT NULL,
  PRIMARY KEY (`NomeReceita`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Doces`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LojaDeDoces`.`Doces` (
  `NomeDoce` VARCHAR(15) NOT NULL,
  `Quantidade` INT NULL,
  `Receitas_NomeReceita` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`NomeDoce`, `Receitas_NomeReceita`),
  INDEX `fk_Doces_Receitas_idx` (`Receitas_NomeReceita` ASC) VISIBLE,
    FOREIGN KEY (`Receitas_NomeReceita`)
    REFERENCES `LojaDeDoces`.`Receitas` (`NomeReceita`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Endereços`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LojaDeDoces`.`Endereços` (
  `Endereco` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`Endereco`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LojaDeDoces`.`Clientes` (
  `CPF` VARCHAR(12) NOT NULL,
  `NomeCliente` VARCHAR(45) NOT NULL,
  `Endereços_Endereco` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`CPF`, `Endereços_Endereco`),
  INDEX `fk_Clientes_Endereços1_idx` (`Endereços_Endereco` ASC) VISIBLE,
    FOREIGN KEY (`Endereços_Endereco`)
    REFERENCES `LojaDeDoces`.`Endereços` (`Endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LojaDeDoces`.`Pedidos` (
  `idPedidos` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idPedidos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Clientes_has_Pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LojaDeDoces`.`Clientes_has_Pedidos` (
  `Clientes_CPF` VARCHAR(12) NOT NULL,
  `Clientes_Endereços_Endereco` VARCHAR(90) NOT NULL,
  `Pedidos_idPedidos` INT NOT NULL,
  PRIMARY KEY (`Clientes_CPF`, `Clientes_Endereços_Endereco`, `Pedidos_idPedidos`),
  INDEX `fk_Clientes_has_Pedidos_Pedidos1_idx` (`Pedidos_idPedidos` ASC) VISIBLE,
  INDEX `fk_Clientes_has_Pedidos_Clientes1_idx` (`Clientes_CPF` ASC, `Clientes_Endereços_Endereco` ASC) VISIBLE,
    FOREIGN KEY (`Clientes_CPF` , `Clientes_Endereços_Endereco`)
    REFERENCES `LojaDeDoces`.`Clientes` (`CPF` , `Endereços_Endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`Pedidos_idPedidos`)
    REFERENCES `LojaDeDoces`.`Pedidos` (`idPedidos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pedidos_has_Doces`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LojaDeDoces`.`Pedidos_has_Doces` (
  `Pedidos_idPedidos` INT NOT NULL,
  `Doces_NomeDoce` VARCHAR(15) NOT NULL,
  `Doces_Receitas_NomeReceita` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Pedidos_idPedidos`, `Doces_NomeDoce`, `Doces_Receitas_NomeReceita`),
  INDEX `fk_Pedidos_has_Doces_Doces1_idx` (`Doces_NomeDoce` ASC, `Doces_Receitas_NomeReceita` ASC) VISIBLE,
  INDEX `fk_Pedidos_has_Doces_Pedidos1_idx` (`Pedidos_idPedidos` ASC) VISIBLE,
    FOREIGN KEY (`Pedidos_idPedidos`)
    REFERENCES `LojaDeDoces`.`Pedidos` (`idPedidos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`Doces_NomeDoce` , `Doces_Receitas_NomeReceita`)
    REFERENCES `LojaDeDoces`.`Doces` (`NomeDoce` , `Receitas_NomeReceita`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
