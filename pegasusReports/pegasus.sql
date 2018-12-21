-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 13, 2017 at 03:27 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO"; SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pegasus`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--
CREATE TABLE IF NOT EXISTS `account` (
 `account_id` INT(11) NOT NULL AUTO_INCREMENT,
 `account_name` VARCHAR(45) DEFAULT NULL,
 `account_com` INT(11) DEFAULT NULL,
 `account_paperCash` DOUBLE DEFAULT NULL,
 `account_loan` DOUBLE DEFAULT NULL,
 `account_noCom` INT(11) DEFAULT NULL,
 `account_lessCom` INT(11) DEFAULT NULL,
 `account_exp` DOUBLE DEFAULT NULL, PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `account`
--
INSERT INTO `account` (`account_id`, `account_name`, `account_com`, `account_paperCash`, `account_loan`, `account_noCom`, `account_lessCom`, `account_exp`) VALUES
(2, 'Ragama', 20, 350, 150, 20, 14, 350),
(3, 'Kochchikade', 20, 350, 1500, 20, 14, 3500),
(4, 'Awissawella', 20, 350, 1500, 20, 14, 680),
(5, 'Pugoda', 20, 150, 300, 20, 9, 500);

-- --------------------------------------------------------

--
-- Table structure for table `center`
--
CREATE TABLE IF NOT EXISTS `center` (
 `center_id` INT(11) NOT NULL AUTO_INCREMENT,
 `center_name` VARCHAR(45) DEFAULT NULL,
 `center_com` INT(11) DEFAULT NULL,
 `center_paperCash` DOUBLE DEFAULT NULL,
 `center_loan` DOUBLE DEFAULT NULL,
 `center_noCom` INT(11) DEFAULT NULL,
 `center_lessCom` INT(11) DEFAULT NULL,
 `center_exp` DOUBLE DEFAULT NULL,
 `center_accountType` VARCHAR(45) DEFAULT NULL,
 `center_status` VARCHAR(45) DEFAULT NULL,
 `account_id` INT(11) NOT NULL, PRIMARY KEY (`center_id`,`account_id`), KEY `fk_Center_Account1_idx` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `center`
--
INSERT INTO `center` (`center_id`, `center_name`, `center_com`, `center_paperCash`, `center_loan`, `center_noCom`, `center_lessCom`, `center_exp`, `center_accountType`, `center_status`, `account_id`) VALUES
(3, 'marambe', 15, 650, 200, 20, 20, 500, 'Normal', 'Active', 2),
(4, 'gatahetta', 15, 15, 2000, 30, 15, 3000, 'Normal', 'Active', 2),
(5, 'Nilanka', 20, 350, 600, 20, 14, 450, 'Normal', 'Active', 2),
(6, 'Douglas', 20, 4150, 150, 20, 14, 350, 'Normal', 'Active', 3),
(7, 'Lucky_new', 20, 10, 300, 20, 9, 15, 'Normal', 'Active', 5);

-- --------------------------------------------------------

--
-- Table structure for table `summery`
--
CREATE TABLE IF NOT EXISTS `summery` (
 `summery_id` INT(11) NOT NULL AUTO_INCREMENT,
 `summery_date` DATE DEFAULT NULL,
 `summery_name` VARCHAR(45) DEFAULT NULL,
 `summery_fc` DOUBLE DEFAULT NULL,
 `summery_cash` DOUBLE DEFAULT NULL,
 `summery_pd` DOUBLE DEFAULT NULL,
 `summery_noComAmount` DOUBLE DEFAULT NULL,
 `summery_lessComAmount` DOUBLE DEFAULT NULL,
 `summery_lcnc` DOUBLE DEFAULT NULL,
 `summery_paperCash` DOUBLE DEFAULT NULL,
 `summery_compay` DOUBLE DEFAULT NULL,
 `summery_expenses` DOUBLE DEFAULT NULL,
 `summery_slippaytot` DOUBLE DEFAULT NULL,
 `summery_loan` DOUBLE DEFAULT NULL,
 `summery_excess` DOUBLE DEFAULT NULL,
 `summery_salary` DOUBLE DEFAULT NULL,
 `center_id` INT(11) NOT NULL, PRIMARY KEY (`summery_id`,`center_id`), KEY `fk_Summery_Center1_idx` (`center_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `summery`
--
INSERT INTO `summery` (`summery_id`, `summery_date`, `summery_name`, `summery_fc`, `summery_cash`, `summery_pd`, `summery_noComAmount`, `summery_lessComAmount`, `summery_lcnc`, `summery_paperCash`, `summery_compay`, `summery_expenses`, `summery_slippaytot`, `summery_loan`, `summery_excess`, `summery_salary`, `center_id`) VALUES
(10, '2017-02-12', 'Douglas', 3500, 4500, 30, 33, 44, 13.420000000000002, 4150, 700, 350, 30, 150, 0, 350, 6),
(11, '2017-02-12', 'Nilanka', 2500, 3500, 222, 40, 30, 11.6, 350, 500, 450, 10, 650, 111, 450, 5),
(12, '2017-02-13', 'Douglas', 2500, 3000, 30, 0, 0, 0, 4150, 500, 350, 60, 150, 0, 350, 6);

-- --------------------------------------------------------

--
-- Table structure for table `summery_line`
--
CREATE TABLE IF NOT EXISTS `summery_line` (
 `summery_line_id` INT(11) NOT NULL AUTO_INCREMENT,
 `summery_line_chitNumber` VARCHAR(45) DEFAULT NULL,
 `summery_line_investment` DOUBLE DEFAULT NULL,
 `summery_line_payment` DOUBLE DEFAULT NULL,
 `summery_line_date` DATE DEFAULT NULL,
 `center_id` INT(11) NOT NULL, PRIMARY KEY (`summery_line_id`,`center_id`), KEY `fk_Summery_line_Summery1_idx` (`center_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=66 ;

--
-- Dumping data for table `summery_line`
--
INSERT INTO `summery_line` (`summery_line_id`, `summery_line_chitNumber`, `summery_line_investment`, `summery_line_payment`, `summery_line_date`, `center_id`) VALUES
(61, '10', 20, 30, '2017-02-12', 6),
(63, '10', 10, 10, '2017-02-12', 5),
(64, '0', 20, 30, '2017-02-13', 6),
(65, '20', 30, 30, '2017-02-13', 6);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `center`
--
ALTER TABLE `center` ADD CONSTRAINT `fk_Center_Account1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON
DELETE NO ACTION ON
UPDATE NO ACTION;

--
-- Constraints for table `summery`
--
ALTER TABLE `summery` ADD CONSTRAINT `fk_Summery_Center1` FOREIGN KEY (`center_id`) REFERENCES `center` (`center_id`) ON
DELETE NO ACTION ON
UPDATE NO ACTION;

--
-- Constraints for table `summery_line`
--
ALTER TABLE `summery_line` ADD CONSTRAINT `fk_Summery_line_Summery1` FOREIGN KEY (`center_id`) REFERENCES `summery` (`center_id`) ON
DELETE NO ACTION ON
UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
