-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: pdb9.awardspace.net
-- Generation Time: Apr 30, 2017 at 04:45 PM
-- Server version: 5.7.18-log
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `2312545_stockers`
--
CREATE DATABASE IF NOT EXISTS `2312545_stockers` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `2312545_stockers`;

-- --------------------------------------------------------

--
-- Table structure for table `CurrentStockPricing`
--

CREATE TABLE `CurrentStockPricing` (
  `CompanyName` text NOT NULL,
  `Ticker` text NOT NULL,
  `Price` decimal(11,2) NOT NULL,
  `Date` text NOT NULL,
  `Time` text NOT NULL,
  `ChangePercent` text NOT NULL,
  `Volume` int(11) NOT NULL,
  `Year` text NOT NULL,
  `Open` text NOT NULL,
  `Cap` text NOT NULL,
  `PE` text NOT NULL,
  `DPS` text NOT NULL,
  `EPS` text NOT NULL,
  `High` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `CurrentStockPricing`
--

INSERT INTO `CurrentStockPricing` (`CompanyName`, `Ticker`, `Price`, `Date`, `Time`, `ChangePercent`, `Volume`, `Year`, `Open`, `Cap`, `PE`, `DPS`, `EPS`, `High`) VALUES
('Microsoft Corporation', 'msft', 68.27, '4/27/2017', '4:00pm', '+0.44 - +0.65%', 34828091, '48.03 - 68.38', '68.15', '527.56B', '32.13', '1.56', '2.12', '1.56'),
('Yahoo! Inc.', 'yhoo', 46.33, '3/27/2017', '11:32am', '-0.07 - -0.15%', 1856381, '34.62 - 47.19', '45.92', '44.31B', 'N/A', 'N/A', '-0.23', 'N/A\n'),
('Alphabet Inc.', 'goog', 815.52, '3/27/2017', '11:32am', '+1.087 - +0.133%', 945564, '663.284 - 853.500', '806.950', '563.86B', '29.254', 'N/A', '27.877', 'N/A\n'),
('Vista Gold Corp Common Stock', 'vgz', 1.04, '3/27/2017', '11:27am', '+0.04 - +4.04%', 75570, '0.43 - 2.09', '1.01', '101.70M', 'N/A', 'N/A', '-0.04', 'N/A\n'),
('Verizon Communications Inc. Com', 'vz', 46.67, '4/27/2017', '4:02pm', '-0.69 - -1.46%', 16835055, '46.01 - 56.95', '47.36', '190.37B', '15.61', '2.31', '2.99', '2.31'),
('AT&T Inc.', 't', 39.91, '4/27/2017', '4:01pm', '-0.53 - -1.31%', 22643138, '36.10 - 43.89', '40.37', '245.11B', '19.01', '1.96', '2.10', '1.96'),
('Target Corporation Common Stock', 'tgt', 56.09, '4/27/2017', '4:01pm', '+0.11 - +0.20%', 3880704, '52.72 - 82.99', '55.98', '31.00B', '11.94', '2.40', '4.70', '2.40'),
('Apple Inc.', 'aapl', 143.79, '4/27/2017', '4:00pm', '+0.11 - +0.08%', 14225487, '89.47 - 145.46', '143.89', '754.40B', '17.26', '2.28', '8.33', '2.28'),
('HP Inc. Common Stock', 'hpq', 18.77, '4/27/2017', '4:03pm', '+0.14 - +0.75%', 9193686, '11.31 - 18.83', '18.70', '31.78B', '12.80', '0.53', '1.47', '0.53'),
('Comcast Corporation', 'cmcsa', 39.59, '4/27/2017', '4:00pm', '+0.80 - +2.06%', 35176516, '29.81 - 40.62', '39.51', '188.12B', '22.18', '0.63', '1.78', '0.63'),
('DISH Network Corporation', 'dish', 65.00, '4/27/2017', '4:00pm', '+1.70 - +2.69%', 4371821, '43.86 - 65.61', '63.97', '30.25B', '21.30', '0.00', '3.05', '0.00'),
('Time Warner Inc. New Common Sto', 'twx', 99.90, '4/27/2017', '4:02pm', '-0.30 - -0.30%', 3018469, '68.97 - 100.60', '100.25', '77.36B', '20.16', '1.61', '4.95', '1.61'),
('CBS Corporation Class B Common ', 'cbs', 66.34, '4/27/2017', '4:00pm', '-0.57 - -0.85%', 2236225, '48.88 - 70.10', '67.22', '27.17B', '23.61', '0.72', '2.81', '0.72');

-- --------------------------------------------------------

--
-- Table structure for table `DailyStockHistory`
--

CREATE TABLE `DailyStockHistory` (
  `CompanyID` int(11) NOT NULL,
  `Open` decimal(11,2) NOT NULL,
  `Close` decimal(11,2) NOT NULL,
  `High` decimal(11,2) NOT NULL,
  `Low` decimal(11,2) NOT NULL,
  `Date` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `friend_requests`
--

CREATE TABLE `friend_requests` (
  `id` int(11) NOT NULL,
  `sender` int(11) NOT NULL,
  `recipient` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friend_requests`
--

INSERT INTO `friend_requests` (`id`, `sender`, `recipient`) VALUES
(5, 62, 69),
(25, 73, 64),
(19, 62, 65);

-- --------------------------------------------------------

--
-- Table structure for table `LeagueData`
--

CREATE TABLE `LeagueData` (
  `LeagueID` int(11) NOT NULL,
  `PlayerID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `LeagueInfo`
--

CREATE TABLE `LeagueInfo` (
  `LeagueID` int(11) NOT NULL,
  `LeagueName` text NOT NULL,
  `LeagueOwnerID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Messages`
--

CREATE TABLE `Messages` (
  `message_id` int(11) NOT NULL,
  `user_email` text NOT NULL,
  `message` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Messages`
--

INSERT INTO `Messages` (`message_id`, `user_email`, `message`, `created_at`) VALUES
(12, 'ryan.kellerman@rutgers.edu', 'I know man this is DOPE bruh', '2017-04-22 00:15:27'),
(11, 'ryan.kellerman@rutgers.edu', 'hey!', '2017-04-22 00:15:17'),
(10, 'frank@rutgers.edu', 'this app is LIT', '2017-04-22 00:14:36'),
(9, 'frank@rutgers.edu', 'ayoooooo', '2017-04-22 00:14:26'),
(8, 'frank@rutgers.edu', 'hello', '2017-04-22 00:14:08'),
(13, 'ryan.kellerman@rutgers.edu', 'I bet karan is sleeping right now LOL', '2017-04-22 00:15:45'),
(14, 'ryan.kellerman@rutgers.edu', 'ayoo', '2017-04-22 00:15:57'),
(15, 'frank@rutgers.edu', 'LMAO^', '2017-04-22 00:18:31'),
(16, 'ryan.kellerman@rutgers.edu', 'wyd, wya?', '2017-04-22 00:32:58'),
(17, 'bot@bot.edu', 'I am not a bot', '2017-04-22 01:01:24'),
(18, 'frank@rutgers.edu', 'hey bot', '2017-04-22 01:08:27'),
(19, 'frank@rutgers.edu', 'whos bot?', '2017-04-22 02:03:30'),
(45, 'namit.patel96@gmail.com', 'Cool, I am considering purchasing 100 shares ', '2017-04-24 20:30:21'),
(44, 'harshpatel40', 'the app recommended it as well', '2017-04-24 20:29:52'),
(25, 'harshpatel40', 'hey guys', '2017-04-23 18:10:10'),
(43, 'harshpatel40', 'i think its a good stock', '2017-04-24 20:29:26'),
(42, 'ryan.kellerman@rutgers.edu', 'What do we think about Apple?', '2017-04-24 20:26:40'),
(41, 'harshpatel40', 'hi ', '2017-04-24 20:14:14'),
(40, 'harshpatel40', 'how is it going', '2017-04-24 20:01:02'),
(46, 'harshpatel40', 'NICE!', '2017-04-24 20:30:43'),
(47, 'harshpatel40', 'I think i will buy some as well', '2017-04-24 20:30:59'),
(48, 'harshpatel40', 'Verizon is also a good buy', '2017-04-24 20:32:57'),
(49, 'ryan.kellerman@rutgers.edu', 'I agree, I bought several shares yesterday', '2017-04-24 20:34:58'),
(50, 'harshpatel40', 'very cool', '2017-04-24 20:40:39'),
(51, 'harshpatel40', 'MSFT is up!', '2017-04-24 20:41:13'),
(52, 'harshpatel40', 'Great!', '2017-04-24 20:41:33'),
(53, 'harshpatel40', 'I might invest too!', '2017-04-24 20:45:57'),
(54, 'harshpatel40', 'this is demo 2', '2017-04-24 21:22:04'),
(55, 'harshpatel40', 'hello', '2017-04-27 22:37:59');

-- --------------------------------------------------------

--
-- Table structure for table `OnUpdateStockHistory`
--

CREATE TABLE `OnUpdateStockHistory` (
  `CompanyID` int(11) NOT NULL,
  `Price` decimal(11,2) NOT NULL,
  `Date` text NOT NULL,
  `Time` text NOT NULL,
  `ChangePercent` text NOT NULL,
  `Volume` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `PlayerHistory`
--

CREATE TABLE `PlayerHistory` (
  `PlayerID` int(11) NOT NULL,
  `CompanyID` int(11) NOT NULL,
  `BuySell` int(1) NOT NULL,
  `Shares` int(11) NOT NULL,
  `Price` decimal(11,2) NOT NULL,
  `Date` text NOT NULL,
  `Time` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `PlayerHistory`
--

INSERT INTO `PlayerHistory` (`PlayerID`, `CompanyID`, `BuySell`, `Shares`, `Price`, `Date`, `Time`) VALUES
(60, 6, 1, 10, 646.80, '03/27/2017', '15:29'),
(61, 13, 1, 10, 1403.30, '03/27/2017', '15:33'),
(62, 6, 1, 1, 64.68, '03/27/2017', '15:36'),
(60, 6, 1, 1, 64.86, '03/27/2017', '15:49'),
(60, 6, 1, 1, 64.90, '03/27/2017', '15:53'),
(60, 6, 1, 1, 64.90, '03/27/2017', '15:53'),
(60, 6, 0, 1, 64.90, '03/27/2017', '15:53'),
(60, 6, 0, 1, 64.90, '03/27/2017', '15:53'),
(60, 6, 0, 1, 64.90, '03/27/2017', '15:53'),
(60, 6, 0, 1, 64.90, '03/27/2017', '15:53'),
(60, 6, 0, 1, 64.90, '03/27/2017', '15:53'),
(60, 6, 0, 1, 64.90, '03/27/2017', '15:53'),
(60, 6, 0, 1, 64.90, '03/27/2017', '15:53'),
(60, 6, 0, 1, 64.90, '03/27/2017', '15:53'),
(60, 6, 0, 1, 64.90, '03/27/2017', '15:53'),
(60, 6, 0, 1, 64.90, '03/27/2017', '15:53'),
(60, 6, 0, 1, 64.90, '03/27/2017', '15:53'),
(60, 6, 0, 1, 64.90, '03/27/2017', '15:54'),
(60, 6, 0, 1, 64.90, '03/27/2017', '15:54'),
(60, 13, 1, 5, 702.80, '03/27/2017', '15:54'),
(60, 9, 1, 20, 20.80, '03/27/2017', '15:55'),
(60, 9, 1, 80, 83.20, '03/27/2017', '15:55'),
(60, 13, 1, 30, 4216.80, '03/27/2017', '15:55'),
(60, 15, 1, 70, 2603.30, '03/27/2017', '15:56'),
(60, 8, 1, 2, 1631.04, '03/27/2017', '15:56'),
(61, 6, 1, 50, 3247.00, '03/27/2017', '16:37'),
(61, 13, 1, 30, 4222.50, '03/27/2017', '16:38'),
(61, 7, 1, 5, 231.65, '03/27/2017', '16:39'),
(61, 8, 1, 1, 815.52, '03/27/2017', '16:40'),
(63, 14, 1, 50, 862.50, '03/27/2017', '16:42'),
(63, 13, 1, 10, 1407.70, '03/27/2017', '16:42'),
(63, 8, 1, 5, 4077.60, '03/27/2017', '16:44'),
(63, 16, 1, 30, 1866.90, '03/27/2017', '16:44'),
(64, 13, 1, 1, 140.82, '03/27/2017', '18:33'),
(60, 18, 1, 10, 676.30, '03/27/2017', '19:19'),
(64, 8, 1, 1, 815.52, '03/27/2017', '19:32'),
(60, 6, 1, 1, 65.16, '03/27/2017', '19:38'),
(60, 15, 0, 70, 2606.10, '03/27/2017', '19:42'),
(60, 6, 1, 2, 130.20, '03/27/2017', '20:32'),
(62, 6, 1, 100, 6510.00, '03/28/2017', '00:17'),
(62, 10, 1, 5, 245.70, '03/28/2017', '00:17'),
(65, 13, 1, 3, 422.64, '03/28/2017', '01:16'),
(65, 13, 1, 3, 422.64, '03/28/2017', '01:16'),
(65, 13, 1, 3, 422.64, '03/28/2017', '01:16'),
(65, 6, 1, 40, 2604.00, '03/28/2017', '01:58'),
(65, 10, 1, 10, 491.40, '03/28/2017', '02:16'),
(64, 13, 1, 41, 5776.08, '03/28/2017', '04:07'),
(60, 6, 1, 10, 648.90, '03/28/2017', '13:53'),
(60, 6, 0, 10, 648.90, '03/28/2017', '13:54'),
(64, 7, 1, 1, 46.33, '03/28/2017', '14:07'),
(64, 18, 1, 1, 67.10, '03/28/2017', '14:08'),
(64, 7, 0, 1, 46.33, '03/28/2017', '14:09'),
(64, 7, 1, 1, 46.33, '03/28/2017', '14:09'),
(64, 13, 1, 20, 2833.80, '03/28/2017', '15:07'),
(61, 14, 1, 4, 69.24, '03/28/2017', '16:32'),
(64, 6, 1, 2, 130.76, '03/28/2017', '20:03'),
(66, 13, 1, 10, 1438.00, '03/29/2017', '05:50'),
(66, 13, 1, 10, 1438.00, '03/29/2017', '05:50'),
(66, 13, 0, 10, 1438.00, '03/29/2017', '05:52'),
(64, 13, 1, 1, 144.09, '03/30/2017', '14:11'),
(64, 13, 0, 1, 144.20, '03/30/2017', '14:14'),
(64, 13, 1, 1, 143.93, '03/30/2017', '22:23'),
(64, 13, 0, 1, 143.93, '03/30/2017', '22:36'),
(65, 13, 1, 39, 5613.27, '03/30/2017', '22:58'),
(64, 13, 0, 62, 8923.66, '03/31/2017', '00:19'),
(66, 10, 1, 10, 490.60, '03/31/2017', '00:31'),
(66, 13, 0, 10, 1439.30, '03/31/2017', '00:34'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 6, 1, 5, 324.75, '04/14/2017', '14:21'),
(64, 13, 1, 5, 705.25, '04/15/2017', '14:54'),
(64, 6, 1, 2, 130.80, '04/18/2017', '19:51'),
(64, 6, 1, 2, 130.80, '04/18/2017', '19:51'),
(64, 6, 1, 2, 130.80, '04/18/2017', '19:51'),
(62, 6, 0, 101, 6606.41, '04/18/2017', '19:53'),
(62, 6, 1, 101, 6606.41, '04/18/2017', '19:53'),
(64, 6, 1, 50, 3269.50, '04/18/2017', '22:17'),
(64, 6, 0, 50, 3269.50, '04/18/2017', '22:17'),
(64, 6, 0, 50, 3269.50, '04/18/2017', '22:18'),
(64, 6, 1, 50, 3269.50, '04/18/2017', '22:18'),
(64, 6, 1, 50, 3269.50, '04/18/2017', '22:18'),
(64, 6, 0, 50, 3275.00, '04/20/2017', '23:22'),
(64, 6, 0, 50, 3275.00, '04/20/2017', '23:22'),
(64, 6, 1, 50, 3275.00, '04/20/2017', '23:22'),
(64, 7, 1, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 1, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 1, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 1, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 1, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 0, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 0, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 0, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 0, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 0, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 0, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 1, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 1, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 1, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 1, 1, 46.33, '04/22/2017', '00:09'),
(64, 7, 1, 1, 46.33, '04/22/2017', '00:10'),
(64, 7, 1, 1, 46.33, '04/22/2017', '00:10'),
(65, 6, 0, 20, 1328.00, '04/23/2017', '01:33'),
(62, 6, 1, 5, 332.00, '04/23/2017', '03:22'),
(62, 13, 1, 3, 426.81, '04/23/2017', '18:11'),
(70, 13, 1, 25, 3556.75, '04/23/2017', '18:47'),
(62, 13, 1, 1, 142.27, '04/23/2017', '21:43'),
(62, 11, 1, 5, 199.65, '04/23/2017', '22:00'),
(71, 13, 1, 25, 3556.75, '04/24/2017', '01:32'),
(71, 6, 1, 10, 664.00, '04/24/2017', '01:33'),
(71, 8, 1, 2, 1631.04, '04/24/2017', '01:34'),
(62, 13, 1, 1, 143.75, '04/24/2017', '18:28'),
(62, 6, 1, 1, 67.42, '04/24/2017', '18:31'),
(62, 18, 1, 1, 66.01, '04/24/2017', '19:56'),
(62, 18, 1, 1, 66.01, '04/24/2017', '19:57'),
(62, 6, 1, 1, 67.65, '04/24/2017', '19:57'),
(62, 18, 1, 1, 66.00, '04/24/2017', '19:58'),
(62, 13, 1, 1, 143.77, '04/24/2017', '19:59'),
(62, 18, 1, 1, 65.95, '04/24/2017', '20:01'),
(71, 10, 1, 5, 235.25, '04/24/2017', '20:04'),
(64, 10, 1, 1, 47.05, '04/24/2017', '20:04'),
(62, 7, 1, 1, 46.33, '04/24/2017', '20:05'),
(62, 18, 0, 4, 263.76, '04/24/2017', '20:06'),
(62, 7, 0, 1, 46.33, '04/24/2017', '20:06'),
(73, 10, 1, 5, 235.15, '04/24/2017', '20:09'),
(62, 6, 1, 1, 67.53, '04/24/2017', '20:24'),
(62, 18, 1, 1, 65.95, '04/24/2017', '20:26'),
(64, 10, 1, 1, 47.05, '04/24/2017', '20:32'),
(64, 10, 1, 1, 47.05, '04/24/2017', '20:32'),
(62, 13, 1, 1, 143.64, '04/24/2017', '20:34'),
(62, 10, 0, 5, 235.25, '04/24/2017', '20:36'),
(62, 13, 0, 7, 1005.48, '04/24/2017', '20:36'),
(62, 18, 0, 1, 65.95, '04/24/2017', '20:37'),
(62, 11, 0, 5, 200.10, '04/24/2017', '20:37'),
(62, 10, 1, 1, 47.05, '04/24/2017', '20:45'),
(62, 10, 0, 1, 47.05, '04/24/2017', '20:46'),
(62, 11, 1, 10, 400.20, '04/24/2017', '20:49'),
(62, 6, 1, 2, 135.06, '04/24/2017', '21:07'),
(62, 6, 0, 2, 135.06, '04/24/2017', '21:07'),
(62, 18, 1, 5, 332.05, '04/25/2017', '22:55');

-- --------------------------------------------------------

--
-- Table structure for table `PlayerInfo`
--

CREATE TABLE `PlayerInfo` (
  `ID` int(11) NOT NULL,
  `FirstName` text NOT NULL,
  `LastName` text NOT NULL,
  `Email` text NOT NULL,
  `Password` text NOT NULL,
  `PlayerValue` decimal(11,2) NOT NULL,
  `friends` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `PlayerInfo`
--

INSERT INTO `PlayerInfo` (`ID`, `FirstName`, `LastName`, `Email`, `Password`, `PlayerValue`, `friends`) VALUES
(60, 'Ryan', 'Kellerman', 'ryan.kellerman@rutgers.edu', 'ryan', 2478.74, 'a:4:{i:0;s:2:"62";i:1;s:2:"65";i:2;s:2:"70";i:3;s:2:"71";}'),
(61, 'Namit', 'Patel', 'namit.patel96@gmail.com', 'wubbalubbadubdub', 10.79, 'a:2:{i:0;s:2:"62";i:1;s:2:"62";}'),
(62, 'harsh', 'patel', 'harshpatel40', 'harsh', 2353.16, 'a:6:{i:0;s:2:"65";i:1;s:2:"60";i:2;s:2:"61";i:3;s:2:"64";i:4;s:2:"72";i:5;s:2:"71";}'),
(63, 'Ely', 'Fialkoff', 'Ely.fialkoff@gmail.com', 'ely', 1785.30, ''),
(64, 'Frank', 'Velazquez', 'frank@rutgers.edu', 'frank123', 5699.91, 'a:2:{i:0;s:2:"62";i:1;s:2:"71";}'),
(65, 'arjun', 'bedi', 'arjun', 'bedi', 1351.41, 'a:2:{i:0;s:2:"62";i:1;s:2:"60";}'),
(66, 'Ashwin', 'Kadaru', 'ashwinkadaru96@hotmail.com', 'catdog', 9510.70, ''),
(70, 'Ashwin', 'Kadaru', 'chiraq@bloods.com', 'test123', 6443.25, 'a:1:{i:0;s:2:"60";}'),
(71, 'Test', 'Account', 'Test@account.com', 'test123', 3912.96, 'a:3:{i:0;s:2:"62";i:1;s:2:"64";i:2;s:2:"62";}'),
(72, 'Software', 'Engineering', 'SE@fun.com', 'test123', 10000.00, 'a:1:{i:0;s:2:"62";}'),
(73, 'Account', 'Test', 'account@test.com', 'test123', 9764.85, '');

-- --------------------------------------------------------

--
-- Table structure for table `StockInfo`
--

CREATE TABLE `StockInfo` (
  `ID` int(11) NOT NULL,
  `CompanyName` text NOT NULL,
  `Ticker` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `StockInfo`
--

INSERT INTO `StockInfo` (`ID`, `CompanyName`, `Ticker`) VALUES
(6, 'Microsoft Corporation', 'msft'),
(7, 'Yahoo! Inc.', 'yhoo'),
(8, 'Alphabet Inc.', 'goog'),
(9, 'Vista Gold Corp Common Stock', 'vgz'),
(10, 'Verizon Communications Inc. Com', 'vz'),
(11, 'AT&T Inc.', 't'),
(12, 'Target Corporation Common Stock', 'tgt'),
(13, 'Apple Inc.', 'aapl'),
(14, 'HP Inc. Common Stock', 'hpq'),
(15, 'Comcast Corporation', 'cmcsa'),
(16, 'DISH Network Corporation', 'dish'),
(17, 'Time Warner Inc. New Common Sto', 'twx'),
(18, 'CBS Corporation Class B Common ', 'cbs');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `DailyStockHistory`
--
ALTER TABLE `DailyStockHistory`
  ADD KEY `CompanyID` (`CompanyID`);

--
-- Indexes for table `friend_requests`
--
ALTER TABLE `friend_requests`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `LeagueData`
--
ALTER TABLE `LeagueData`
  ADD KEY `LeagueID` (`LeagueID`,`PlayerID`),
  ADD KEY `PlayerID` (`PlayerID`);

--
-- Indexes for table `LeagueInfo`
--
ALTER TABLE `LeagueInfo`
  ADD PRIMARY KEY (`LeagueID`),
  ADD KEY `LeagueOwnerID` (`LeagueOwnerID`);

--
-- Indexes for table `Messages`
--
ALTER TABLE `Messages`
  ADD PRIMARY KEY (`message_id`);

--
-- Indexes for table `OnUpdateStockHistory`
--
ALTER TABLE `OnUpdateStockHistory`
  ADD KEY `CompanyID` (`CompanyID`);

--
-- Indexes for table `PlayerHistory`
--
ALTER TABLE `PlayerHistory`
  ADD KEY `PlayerID` (`PlayerID`,`CompanyID`),
  ADD KEY `CompanyID` (`CompanyID`);

--
-- Indexes for table `PlayerInfo`
--
ALTER TABLE `PlayerInfo`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `StockInfo`
--
ALTER TABLE `StockInfo`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `friend_requests`
--
ALTER TABLE `friend_requests`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `LeagueInfo`
--
ALTER TABLE `LeagueInfo`
  MODIFY `LeagueID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Messages`
--
ALTER TABLE `Messages`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;
--
-- AUTO_INCREMENT for table `PlayerInfo`
--
ALTER TABLE `PlayerInfo`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;
--
-- AUTO_INCREMENT for table `StockInfo`
--
ALTER TABLE `StockInfo`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `DailyStockHistory`
--
ALTER TABLE `DailyStockHistory`
  ADD CONSTRAINT `dailystockhistory_ibfk_1` FOREIGN KEY (`CompanyID`) REFERENCES `StockInfo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `LeagueData`
--
ALTER TABLE `LeagueData`
  ADD CONSTRAINT `leaguedata_ibfk_1` FOREIGN KEY (`LeagueID`) REFERENCES `LeagueInfo` (`LeagueID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `leaguedata_ibfk_2` FOREIGN KEY (`PlayerID`) REFERENCES `PlayerInfo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `LeagueInfo`
--
ALTER TABLE `LeagueInfo`
  ADD CONSTRAINT `leagueinfo_ibfk_1` FOREIGN KEY (`LeagueOwnerID`) REFERENCES `PlayerInfo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `OnUpdateStockHistory`
--
ALTER TABLE `OnUpdateStockHistory`
  ADD CONSTRAINT `onupdatestockhistory_ibfk_1` FOREIGN KEY (`CompanyID`) REFERENCES `StockInfo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `PlayerHistory`
--
ALTER TABLE `PlayerHistory`
  ADD CONSTRAINT `playerhistory_ibfk_1` FOREIGN KEY (`PlayerID`) REFERENCES `PlayerInfo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `playerhistory_ibfk_2` FOREIGN KEY (`CompanyID`) REFERENCES `StockInfo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
