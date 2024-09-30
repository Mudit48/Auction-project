-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 30, 2024 at 04:32 PM
-- Server version: 8.0.39
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `active_bids`
--

DROP TABLE IF EXISTS `active_bids`;
CREATE TABLE IF NOT EXISTS `active_bids` (
  `item_id` int DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `start_price` int DEFAULT NULL,
  `min_incr` int DEFAULT NULL,
  `payout_price` int DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  `seller_id` int DEFAULT NULL,
  `curr_max` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `active_bids`
--

INSERT INTO `active_bids` (`item_id`, `name`, `description`, `start_price`, `min_incr`, `payout_price`, `end_date`, `seller_id`, `curr_max`) VALUES
(1, 'balls', 'they are balls', 600, 0, 100, '2024-09-26 09:15:59', NULL, NULL),
(2, 'ball2', 'bigbal', 808, 22, 333, '2024-09-26 18:30:00', NULL, NULL),
(3, 'ba;', 'bigbigbal', 8088, 500, 10000, '2024-09-29 18:30:00', 1, 3000),
(4, 'ball', 'balls yes', 85858, 400, 50000, '2024-09-29 18:30:00', 1, NULL),
(5, 'huge bal', 'very big bal', 700, 70, 1200, '2024-10-01 18:30:00', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `auctest`
--

DROP TABLE IF EXISTS `auctest`;
CREATE TABLE IF NOT EXISTS `auctest` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `ph_no` int NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `credit_info` bigint DEFAULT NULL,
  `cvv` int DEFAULT NULL,
  `balance` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `auctest`
--

INSERT INTO `auctest` (`user_id`, `name`, `email`, `city`, `state`, `ph_no`, `password`, `credit_info`, `cvv`, `balance`) VALUES
(1, 'mudit', 'gmail', 'bablapur', 'maharastra', 8087, '8087', 1234567890, 123, 1000),
(2, 'mudiot', '@gmail', 'bab', 'maha', 8087, 'whaetyvr', 9876543210, 456, 500),
(3, 'loukik', 'loukik@gmail.com', 'parel', 'maharashtra', 8766, 'loukik', 1111111111, 789, 2000),
(5, 'mudit', 'test', 'bab', 'maharastra', 8087, 'test', 2222222222, 901, 300000);

-- --------------------------------------------------------

--
-- Table structure for table `bidder`
--

DROP TABLE IF EXISTS `bidder`;
CREATE TABLE IF NOT EXISTS `bidder` (
  `bid_id` int NOT NULL AUTO_INCREMENT,
  `bname` varchar(20) DEFAULT NULL,
  `item_id` int DEFAULT NULL,
  `credit_info` bigint DEFAULT NULL,
  `cvv` int DEFAULT NULL,
  `balance` int DEFAULT NULL,
  `bidder_id` int DEFAULT NULL,
  PRIMARY KEY (`bid_id`),
  KEY `item_id` (`item_id`),
  KEY `fk_bidder_auctest` (`bidder_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bidder`
--

INSERT INTO `bidder` (`bid_id`, `bname`, `item_id`, `credit_info`, `cvv`, `balance`, `bidder_id`) VALUES
(1, 'mudit', 6, 1234567890, 123, 1000, 5),
(2, 'mudit', 4, 9087654321, 421, 10000, 5),
(3, 'mudit', 3, 1111111111, 420, 37000, 5),
(4, 'mudit', 3, NULL, NULL, NULL, 5),
(5, 'mudit', 5, NULL, NULL, NULL, 5),
(6, 'mudit', 3, NULL, NULL, NULL, 5),
(7, 'mudit', 3, NULL, NULL, NULL, 5),
(8, 'mudit', 3, NULL, NULL, NULL, 5),
(9, 'mudit', 7, NULL, NULL, NULL, 5),
(10, 'mudit', 7, NULL, NULL, NULL, 5),
(11, 'mudit', 7, NULL, NULL, NULL, 5),
(12, 'mudit', 7, NULL, NULL, NULL, 5),
(13, 'mudit', 7, NULL, NULL, NULL, 5),
(14, 'mudit', 7, NULL, NULL, NULL, 5),
(15, 'mudit', 7, NULL, NULL, NULL, 5),
(16, 'mudit', 7, NULL, NULL, NULL, 5),
(17, 'mudit', 7, NULL, NULL, NULL, 5),
(18, 'mudit', 7, NULL, NULL, NULL, 5);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
CREATE TABLE IF NOT EXISTS `items` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(100) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `image` blob,
  `start_price` int DEFAULT NULL,
  `min_incr` int DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `payout_price` int DEFAULT NULL,
  `buyer_id` int DEFAULT NULL,
  `seller_id` int DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `fk_buyer` (`buyer_id`),
  KEY `fk_seller` (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`item_id`, `description`, `name`, `image`, `start_price`, `min_incr`, `end_date`, `payout_price`, `buyer_id`, `seller_id`) VALUES
(1, 'they are balls', 'balls', NULL, 600, 0, '2024-09-26 14:45:59', 100, NULL, NULL),
(2, 'bigbal', 'ball2', NULL, 808, 22, '2024-09-27 00:00:00', 333, NULL, NULL),
(3, 'bigbigbal', 'ba;', NULL, 8088, 500, '2024-09-30 00:00:00', 10000, NULL, 1),
(4, 'balls yes', 'ball', NULL, 85858, 400, '2024-09-30 00:00:00', 50000, NULL, 1),
(5, 'very big bal', 'huge bal', NULL, 700, 70, '2024-10-02 00:00:00', 1200, NULL, 1),
(6, 'bigbigbigballs', 'balls', NULL, 80000, 300, '2024-10-02 00:00:00', 10000, NULL, 5),
(7, 'mouse that plays games', 'gaming mouse', NULL, 3500, 200, '2024-10-02 00:00:00', 5000, NULL, 5);

-- --------------------------------------------------------

--
-- Table structure for table `seller`
--

DROP TABLE IF EXISTS `seller`;
CREATE TABLE IF NOT EXISTS `seller` (
  `seller_id` int NOT NULL,
  `seller_fname` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `ph_no` int DEFAULT NULL,
  KEY `seller_id` (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `seller`
--

INSERT INTO `seller` (`seller_id`, `seller_fname`, `email`, `city`, `state`, `ph_no`) VALUES
(1, 'mudit', 'gmail', 'bablapur', 'maharastra', 8087),
(1, 'mudit', 'gmail', 'bablapur', 'maharastra', 8087),
(5, 'mudit', 'test', 'bab', 'maharastra', 8087),
(5, 'mudit', 'test', 'bab', 'maharastra', 8087);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE IF NOT EXISTS `transactions` (
  `trsc_id` int NOT NULL AUTO_INCREMENT,
  `amount` int DEFAULT NULL,
  `c_num` int DEFAULT NULL,
  `cvv` int DEFAULT NULL,
  `bid_id` int DEFAULT NULL,
  PRIMARY KEY (`trsc_id`),
  KEY `bid_id` (`bid_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`trsc_id`, `amount`, `c_num`, `cvv`, `bid_id`) VALUES
(1, 3000, 1111111111, 420, 3);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bidder`
--
ALTER TABLE `bidder`
  ADD CONSTRAINT `bidder_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`),
  ADD CONSTRAINT `fk_bidder_auctest` FOREIGN KEY (`bidder_id`) REFERENCES `auctest` (`user_id`);

--
-- Constraints for table `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `fk_buyer` FOREIGN KEY (`buyer_id`) REFERENCES `auctest` (`user_id`),
  ADD CONSTRAINT `fk_seller` FOREIGN KEY (`seller_id`) REFERENCES `auctest` (`user_id`);

--
-- Constraints for table `seller`
--
ALTER TABLE `seller`
  ADD CONSTRAINT `seller_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `items` (`seller_id`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`bid_id`) REFERENCES `bidder` (`bid_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
