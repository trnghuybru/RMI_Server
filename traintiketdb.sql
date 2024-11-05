-- MySQL Script generated by MySQL Workbench
-- Sun 03 Nov 2024 07:31:23 PM +07
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema traintiketdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema traintiketdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `traintiketdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `traintiketdb` ;

-- -----------------------------------------------------
-- Table `traintiketdb`.`banggia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `traintiketdb`.`banggia` (
  `BangGiaID` INT NOT NULL AUTO_INCREMENT,
  `TauID` INT NOT NULL,
  `GheID` INT NOT NULL,
  `GaDi` INT NOT NULL,
  `GaDen` INT NOT NULL,
  `GiaTien` FLOAT NOT NULL,
  PRIMARY KEY (`BangGiaID`))
ENGINE = InnoDB
AUTO_INCREMENT = 222
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `traintiketdb`.`ga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `traintiketdb`.`ga` (
  `GaID` INT NOT NULL AUTO_INCREMENT,
  `Ten` VARCHAR(200) NOT NULL,
  `DiaChi` VARCHAR(200) NOT NULL,
  `SDT` VARCHAR(18) NOT NULL,
  PRIMARY KEY (`GaID`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `traintiketdb`.`nhanvien`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `traintiketdb`.`nhanvien` (
  `NhanVienID` INT NOT NULL AUTO_INCREMENT,
  `Ten` VARCHAR(50) NOT NULL,
  `SDT` VARCHAR(11) NOT NULL,
  `Email` VARCHAR(255) NOT NULL,
  `Password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`NhanVienID`),
  UNIQUE INDEX `Email` (`Email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `traintiketdb`.`hoadon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `traintiketdb`.`hoadon` (
  `HoaDonID` INT NOT NULL AUTO_INCREMENT,
  `TenKH` VARCHAR(50) NOT NULL,
  `DiaChi` VARCHAR(200) NOT NULL,
  `SDT` VARCHAR(11) NOT NULL,
  `ThoiGian` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `NhanVienID` INT NOT NULL,
  `SoTien` FLOAT NOT NULL,
  PRIMARY KEY (`HoaDonID`),
  INDEX `NhanVienID` (`NhanVienID` ASC) VISIBLE,
  CONSTRAINT `hoadon_ibfk_1`
    FOREIGN KEY (`NhanVienID`)
    REFERENCES `traintiketdb`.`nhanvien` (`NhanVienID`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `traintiketdb`.`chitiethoadon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `traintiketdb`.`chitiethoadon` (
  `CTHDID` INT NOT NULL AUTO_INCREMENT,
  `GheID` INT NOT NULL,
  `TauID` INT NOT NULL,
  `GaDi` INT NOT NULL,
  `GaDen` INT NOT NULL,
  `HoaDonID` INT NOT NULL,
  `NgayKhoiHanh` DATETIME NOT NULL,
  PRIMARY KEY (`CTHDID`),
  INDEX `GaDi` (`GaDi` ASC) VISIBLE,
  INDEX `HoaDonID` (`HoaDonID` ASC) VISIBLE,
  CONSTRAINT `chitiethoadon_ibfk_1`
    FOREIGN KEY (`GaDi`)
    REFERENCES `traintiketdb`.`ga` (`GaID`),
  CONSTRAINT `chitiethoadon_ibfk_2`
    FOREIGN KEY (`HoaDonID`)
    REFERENCES `traintiketdb`.`hoadon` (`HoaDonID`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `traintiketdb`.`loaighe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `traintiketdb`.`loaighe` (
  `LoaiGheID` INT NOT NULL AUTO_INCREMENT,
  `Ten` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`LoaiGheID`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `traintiketdb`.`toa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `traintiketdb`.`toa` (
  `ToaID` INT NOT NULL AUTO_INCREMENT,
  `TenToa` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ToaID`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `traintiketdb`.`ghe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `traintiketdb`.`ghe` (
  `GheID` INT NOT NULL AUTO_INCREMENT,
  `SoGhe` INT NOT NULL,
  `LoaiGheID` INT NOT NULL,
  `ToaID` INT NOT NULL,
  PRIMARY KEY (`GheID`),
  INDEX `LoaiGheID` (`LoaiGheID` ASC) VISIBLE,
  INDEX `ToaID` (`ToaID` ASC) VISIBLE,
  CONSTRAINT `ghe_ibfk_1`
    FOREIGN KEY (`LoaiGheID`)
    REFERENCES `traintiketdb`.`loaighe` (`LoaiGheID`),
  CONSTRAINT `ghe_ibfk_2`
    FOREIGN KEY (`ToaID`)
    REFERENCES `traintiketdb`.`toa` (`ToaID`))
ENGINE = InnoDB
AUTO_INCREMENT = 115
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `traintiketdb`.`tuyen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `traintiketdb`.`tuyen` (
  `TuyenID` INT NOT NULL AUTO_INCREMENT,
  `Ten` VARCHAR(255) NOT NULL,
  `Huong` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`TuyenID`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `traintiketdb`.`tau`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `traintiketdb`.`tau` (
  `TauID` INT NOT NULL AUTO_INCREMENT,
  `TenTau` VARCHAR(255) NOT NULL,
  `TuyenID` INT NOT NULL,
  PRIMARY KEY (`TauID`),
  INDEX `TuyenID` (`TuyenID` ASC) VISIBLE,
  CONSTRAINT `tau_ibfk_1`
    FOREIGN KEY (`TuyenID`)
    REFERENCES `traintiketdb`.`tuyen` (`TuyenID`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `traintiketdb`.`giotau`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `traintiketdb`.`giotau` (
  `GioID` INT NOT NULL AUTO_INCREMENT,
  `GaID` INT NOT NULL,
  `TauID` INT NOT NULL,
  `GioDi` DATETIME NOT NULL,
  `GioDen` DATETIME NOT NULL,
  `GaDenID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`GioID`),
  INDEX `GaID` (`GaID` ASC) VISIBLE,
  INDEX `TauID` (`TauID` ASC) VISIBLE,
  INDEX `giotau_ibfk_3` (`GaDenID` ASC) VISIBLE,
  CONSTRAINT `giotau_ibfk_1`
    FOREIGN KEY (`GaID`)
    REFERENCES `traintiketdb`.`ga` (`GaID`),
  CONSTRAINT `giotau_ibfk_2`
    FOREIGN KEY (`TauID`)
    REFERENCES `traintiketdb`.`tau` (`TauID`),
  CONSTRAINT `giotau_ibfk_3`
    FOREIGN KEY (`GaDenID`)
    REFERENCES `traintiketdb`.`ga` (`GaID`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `traintiketdb`.`tautoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `traintiketdb`.`tautoa` (
  `TauToaID` INT NOT NULL AUTO_INCREMENT,
  `TauID` INT NOT NULL,
  `ToaID` INT NOT NULL,
  PRIMARY KEY (`TauToaID`),
  INDEX `TauID` (`TauID` ASC) VISIBLE,
  INDEX `ToaID` (`ToaID` ASC) VISIBLE,
  CONSTRAINT `tautoa_ibfk_1`
    FOREIGN KEY (`TauID`)
    REFERENCES `traintiketdb`.`tau` (`TauID`),
  CONSTRAINT `tautoa_ibfk_2`
    FOREIGN KEY (`ToaID`)
    REFERENCES `traintiketdb`.`toa` (`ToaID`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
