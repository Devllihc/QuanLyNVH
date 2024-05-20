-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 02, 2024 at 06:34 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nvhmanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `userid` varchar(30) NOT NULL,
  `accountname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `note` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`userid`, `accountname`, `password`, `note`) VALUES
('333', 'tuando12', 'tuan12', NULL),
('361', 'admin', '1', NULL),
('362', 'tranthuan', 'thuan1', 'test1'),
('363', 'hoangtranvan1', '111111', 'test1'),
('366', 'tuando1', 'tuan1', NULL),
('367', 'thuan', '1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `activity`
--

CREATE TABLE `activity` (
  `activityid` int(11) NOT NULL,
  `activityname` varchar(50) NOT NULL,
  `roomid` int(11) NOT NULL,
  `timestart` datetime NOT NULL,
  `timefinish` datetime NOT NULL,
  `note` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `activity`
--

INSERT INTO `activity` (`activityid`, `activityname`, `roomid`, `timestart`, `timefinish`, `note`) VALUES
(6, 'họp lớp1', 3, '2021-12-11 11:12:12', '2021-12-11 18:12:12', ''),
(8, 'ngồi ', 3, '2024-01-02 16:55:44', '2024-01-02 23:55:44', ''),
(9, 'nằm', 4, '2024-01-02 00:00:00', '2024-01-02 08:00:00', '');

-- --------------------------------------------------------

--
-- Table structure for table `device`
--

CREATE TABLE `device` (
  `deviceid` int(11) NOT NULL,
  `devicename` varchar(30) NOT NULL,
  `amount` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `status` varchar(30) NOT NULL,
  `roomid` int(11) NOT NULL,
  `note` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `device`
--

INSERT INTO `device` (`deviceid`, `devicename`, `amount`, `price`, `status`, `roomid`, `note`) VALUES
(2, 'ghế', 100, 1000, 'ok', 1, NULL),
(3, 'bàn', 20, 1000, 'ok', 1, ''),
(4, 'mic', 1, 1000, 'broken', 1, NULL),
(5, 'mic', 5, 1000, 'ok', 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `deviceactivity`
--

CREATE TABLE `deviceactivity` (
  `activityid` int(11) NOT NULL,
  `deviceid` int(11) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `deviceactivity`
--

INSERT INTO `deviceactivity` (`activityid`, `deviceid`, `amount`) VALUES
(0, 0, 5),
(0, 0, 5),
(0, 2, 1),
(6, 2, 10),
(6, 3, 10);

-- --------------------------------------------------------

--
-- Table structure for table `devicerent`
--

CREATE TABLE `devicerent` (
  `rentid` int(11) NOT NULL,
  `deviceid` int(11) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `manageraccount`
--

CREATE TABLE `manageraccount` (
  `userid` varchar(30) NOT NULL,
  `accountname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `secques` varchar(50) DEFAULT NULL,
  `answer` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `manageraccount`
--

INSERT INTO `manageraccount` (`userid`, `accountname`, `password`, `secques`, `answer`) VALUES
('361', 'thuan123', '1', '1+1', '2'),
('365', 'tuando', '1', '1+1', '2');

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `hostid` varchar(30) NOT NULL,
  `sdt` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  `note` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `name`, `hostid`, `sdt`, `address`, `status`, `note`) VALUES
('361', 'do minh tuan', '362', '0123456789', 'nam dinh', 'trong pho', NULL),
('362', 'tran thuan', '362', '0123456788', 'nam dinh', 'trong pho', NULL),
('363', 'hoang', '362', '123', 'nam dinh', 'o nha', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `rent`
--

CREATE TABLE `rent` (
  `rentid` int(11) NOT NULL,
  `rentname` varchar(50) NOT NULL,
  `roomid` varchar(50) NOT NULL,
  `timestart` datetime NOT NULL,
  `timefinish` datetime NOT NULL,
  `renterid` varchar(50) NOT NULL,
  `note` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rent`
--

INSERT INTO `rent` (`rentid`, `rentname`, `roomid`, `timestart`, `timefinish`, `renterid`, `note`) VALUES
(8, '1', '4', '2024-02-02 00:00:00', '2024-02-02 00:00:00', '361', ''),
(9, 'abc', '4', '2024-02-02 05:00:00', '2024-02-02 09:00:00', '362', '');

-- --------------------------------------------------------

--
-- Table structure for table `renter`
--

CREATE TABLE `renter` (
  `id` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `sdt` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `note` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `hostid` varchar(11) NOT NULL,
  `request` varchar(100) NOT NULL,
  `note` varchar(50) NOT NULL,
  `requestid` int(11) NOT NULL,
  `time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`hostid`, `request`, `note`, `requestid`, `time`) VALUES
('362', 'chuyen nha', '', 1, NULL),
('362', 'chuyen nha', 'kcj', 2, NULL),
('362', 'chuyennha1', 'kcj', 3, '2023-12-25 09:57:29'),
('363', 'chuyennha', 'kcj', 4, '2023-12-25 09:50:05'),
('336', 'chuyennha', 'null', 5, '2023-01-01 12:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `roomid` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `capacity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `status` varchar(30) NOT NULL,
  `note` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`roomid`, `name`, `capacity`, `price`, `status`, `note`) VALUES
(3, 'phòng họp', 51, 1000, 'ok', ''),
(4, 'phòng đoàn', 50, 1000, 'ok', '');

-- --------------------------------------------------------

--
-- Table structure for table `work`
--

CREATE TABLE `work` (
  `userid` int(11) NOT NULL,
  `timeloggin` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `work`
--

INSERT INTO `work` (`userid`, `timeloggin`) VALUES
(361, '2023-12-25 12:40:37');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`activityid`);

--
-- Indexes for table `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`deviceid`);

--
-- Indexes for table `manageraccount`
--
ALTER TABLE `manageraccount`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rent`
--
ALTER TABLE `rent`
  ADD PRIMARY KEY (`rentid`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`requestid`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomid`);

--
-- Indexes for table `work`
--
ALTER TABLE `work`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activity`
--
ALTER TABLE `activity`
  MODIFY `activityid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `device`
--
ALTER TABLE `device`
  MODIFY `deviceid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `rent`
--
ALTER TABLE `rent`
  MODIFY `rentid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
  MODIFY `requestid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `roomid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
