-- MySQL Script generated by MySQL Workbench
-- Wed Jun 21 22:24:05 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema locMnsA
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema locMnsA
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `locMnsA` DEFAULT CHARACTER SET utf8 ;
USE `locMnsA` ;

-- -----------------------------------------------------
-- Table `locMnsA`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locMnsA`.`status` (
                                                  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                  `name` VARCHAR(45) NOT NULL,
                                                  PRIMARY KEY (`id`),
                                                  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locMnsA`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locMnsA`.`user` (
                                                `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                `lastname` VARCHAR(45) NOT NULL,
                                                `firstname` VARCHAR(45) NOT NULL,
                                                `email` VARCHAR(70) NOT NULL,
                                                `password` VARCHAR(50) NOT NULL,
                                                `login` VARCHAR(45) NOT NULL,
                                                `gender` VARCHAR(45) NOT NULL,
                                                `affiliation` VARCHAR(45) BINARY NOT NULL,
                                                `phone` VARCHAR(15) NOT NULL,
                                                `statusId` INT UNSIGNED NOT NULL,
                                                PRIMARY KEY (`id`),
                                                UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
                                                UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
                                                UNIQUE INDEX `phoneNumber_UNIQUE` (`phone` ASC) VISIBLE,
                                                INDEX `fk_utilisateur_statut_idx` (`statusId` ASC) VISIBLE,
                                                CONSTRAINT `fk_utilisateur_statut`
                                                    FOREIGN KEY (`statusId`)
                                                        REFERENCES `locMnsA`.`status` (`id`)
                                                        ON DELETE NO ACTION
                                                        ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locMnsA`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locMnsA`.`location` (
                                                    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                    `creationDate` DATE NOT NULL,
                                                    `reason` VARCHAR(500) NOT NULL,
                                                    `previsionnalEndDate` DATE NOT NULL,
                                                    `previsionnalStartingDate` DATE NOT NULL,
                                                    `extensionDate` DATE NULL,
                                                    `extensionValidationDate` DATE NULL,
                                                    `status` VARCHAR(45) NOT NULL,
                                                    `decisionDate` DATE NULL,
                                                    `userId` INT UNSIGNED NOT NULL,
                                                    PRIMARY KEY (`id`),
                                                    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                    INDEX `fk_location_utilisateur_idx` (`userId` ASC) VISIBLE,
                                                    CONSTRAINT `fk_location_utilisateur`
                                                        FOREIGN KEY (`userId`)
                                                            REFERENCES `locMnsA`.`user` (`id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locMnsA`.`storageArea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locMnsA`.`storageArea` (
                                                       `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                       `name` VARCHAR(100) NOT NULL,
                                                       PRIMARY KEY (`id`),
                                                       UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locMnsA`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locMnsA`.`category` (
                                                    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                    `name` VARCHAR(100) NOT NULL,
                                                    PRIMARY KEY (`id`),
                                                    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locMnsA`.`brand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locMnsA`.`brand` (
                                                 `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                 `name` VARCHAR(100) NOT NULL,
                                                 PRIMARY KEY (`id`),
                                                 UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                 UNIQUE INDEX `matricule_materiel_UNIQUE` (`name` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locMnsA`.`model`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locMnsA`.`model` (
                                                 `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                 `name` VARCHAR(100) NOT NULL,
                                                 `brandId` INT UNSIGNED NOT NULL,
                                                 PRIMARY KEY (`id`),
                                                 UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                 UNIQUE INDEX `matricule_materiel_UNIQUE` (`name` ASC) VISIBLE,
                                                 INDEX `fk_model_brand_idx` (`brandId` ASC) VISIBLE,
                                                 CONSTRAINT `fk_model_brand`
                                                     FOREIGN KEY (`brandId`)
                                                         REFERENCES `locMnsA`.`brand` (`id`)
                                                         ON DELETE NO ACTION
                                                         ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locMnsA`.`documentation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locMnsA`.`documentation` (
                                                         `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                         `description` VARCHAR(100) NOT NULL,
                                                         PRIMARY KEY (`id`),
                                                         UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locMnsA`.`material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locMnsA`.`material` (
                                                    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                    `registrationNumber` DECIMAL(10) NOT NULL,
                                                    `storageAreaId` INT UNSIGNED NOT NULL,
                                                    `categoryId` INT UNSIGNED NOT NULL,
                                                    `modelId` INT UNSIGNED NOT NULL,
                                                    `documentationId` INT UNSIGNED NOT NULL,
                                                    PRIMARY KEY (`id`),
                                                    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                    UNIQUE INDEX `matricule_materiel_UNIQUE` (`registrationNumber` ASC) VISIBLE,
                                                    INDEX `fk_material_storageArea_idx` (`storageAreaId` ASC) VISIBLE,
                                                    INDEX `fk_material_category_idx` (`categoryId` ASC) VISIBLE,
                                                    INDEX `fk_material_model_idx` (`modelId` ASC) VISIBLE,
                                                    INDEX `fk_material_documentation_idx` (`documentationId` ASC) VISIBLE,
                                                    CONSTRAINT `fk_material_storageArea`
                                                        FOREIGN KEY (`storageAreaId`)
                                                            REFERENCES `locMnsA`.`storageArea` (`id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION,
                                                    CONSTRAINT `fk_material_category`
                                                        FOREIGN KEY (`categoryId`)
                                                            REFERENCES `locMnsA`.`category` (`id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION,
                                                    CONSTRAINT `fk_material_model`
                                                        FOREIGN KEY (`modelId`)
                                                            REFERENCES `locMnsA`.`model` (`id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION,
                                                    CONSTRAINT `fk_material_documentation`
                                                        FOREIGN KEY (`documentationId`)
                                                            REFERENCES `locMnsA`.`documentation` (`id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locMnsA`.`state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locMnsA`.`state` (
                                                 `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                 `name` VARCHAR(100) NOT NULL,
                                                 PRIMARY KEY (`id`),
                                                 UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locMnsA`.`location_material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locMnsA`.`location_material` (
                                                             `locationId` INT UNSIGNED NOT NULL,
                                                             `materialId` INT UNSIGNED NOT NULL,
                                                             PRIMARY KEY (`locationId`, `materialId`),
                                                             INDEX `fk_location_materiel_materiel_idx` (`materialId` ASC) VISIBLE,
                                                             CONSTRAINT `fk_location_materiel_location`
                                                                 FOREIGN KEY (`locationId`)
                                                                     REFERENCES `locMnsA`.`location` (`id`)
                                                                     ON DELETE NO ACTION
                                                                     ON UPDATE NO ACTION,
                                                             CONSTRAINT `fk_location_materiel_materiel`
                                                                 FOREIGN KEY (`materialId`)
                                                                     REFERENCES `locMnsA`.`material` (`id`)
                                                                     ON DELETE NO ACTION
                                                                     ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locMnsA`.`material_state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locMnsA`.`material_state` (
                                                          `materialId` INT UNSIGNED NOT NULL,
                                                          `stateId` INT UNSIGNED NOT NULL,
                                                          `date` DATETIME NOT NULL,
                                                          PRIMARY KEY (`materialId`, `stateId`),
                                                          INDEX `fk_material_state_state_idx` (`stateId` ASC) VISIBLE,
                                                          CONSTRAINT `fk_material_state_material`
                                                              FOREIGN KEY (`materialId`)
                                                                  REFERENCES `locMnsA`.`material` (`id`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION,
                                                          CONSTRAINT `fk_material_state_state`
                                                              FOREIGN KEY (`stateId`)
                                                                  REFERENCES `locMnsA`.`state` (`id`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;