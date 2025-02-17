-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 02, 2025 at 12:42 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `airline_reservation_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `airlines`
--

CREATE TABLE `airlines` (
  `Airline_ID` int(10) NOT NULL,
  `Airline_Name` varchar(100) NOT NULL,
  `Headquaters` varchar(100) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `airlines`
--

INSERT INTO `airlines` (`Airline_ID`, `Airline_Name`, `Headquaters`, `Status`) VALUES
(1, 'Emirates Airlines', 'Dubai, UAE', 'Active'),
(2, 'Singapore Airlines', 'Singapore', 'Active'),
(3, 'Qatar Airways', 'Doha, Qatar', 'Active'),
(4, 'Lufthansa', 'Frankfurt, Germany', 'Active'),
(5, 'Air France', 'Paris, France', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `baggage`
--

CREATE TABLE `baggage` (
  `baggageID` int(11) NOT NULL,
  `addOnWeight` int(2) NOT NULL,
  `addOnPrice` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `baggage`
--

INSERT INTO `baggage` (`baggageID`, `addOnWeight`, `addOnPrice`) VALUES
(1, 15, 61.60),
(2, 20, 78.40),
(3, 25, 96.60),
(4, 30, 123.30),
(5, 40, 190.40),
(6, 50, 247.80),
(7, 60, 334.60);

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `BookingID` int(10) NOT NULL,
  `Customer_ID` int(10) DEFAULT NULL,
  `Flight_ID` int(10) NOT NULL,
  `Seat_ID` int(10) NOT NULL,
  `booking_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `baggageID` int(11) DEFAULT NULL,
  `paymentMethod` varchar(20) NOT NULL,
  `totalPrice` decimal(10,2) DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`BookingID`, `Customer_ID`, `Flight_ID`, `Seat_ID`, `booking_date`, `baggageID`, `paymentMethod`, `totalPrice`, `deleted_at`) VALUES
(1, 1, 1, 5, '2025-02-02 01:42:11', NULL, 'Debit Card', 1500.00, NULL),
(3, 1, 1, 10, '2025-02-02 11:32:44', 1, 'Credit Card', 1561.60, '2025-02-02 11:33:02'),
(2, 1, 2, 30, '2025-02-02 08:04:49', 1, 'Credit Card', 1561.60, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `Customer_ID` int(10) NOT NULL,
  `Cus_Gender` char(2) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `customer_name` varchar(100) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `customer_email` varchar(100) DEFAULT NULL,
  `customer_phone` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `username` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`Customer_ID`, `Cus_Gender`, `Password`, `customer_name`, `Address`, `customer_email`, `customer_phone`, `status`, `username`) VALUES
(1, 'F', 'ACY35921', 'ANG', 'No 65,Jalan 2,Taman Sri Tanjong,Tanjong Karang,Selangor', 'acy35921@email.com', '017-22334455', 'Active', 'acy'),
(2, 'F', 'cde123', 'CHAI YI JING', ' Jalan Mengkuang Off Jalan Ru Off Jalan Ampang', 'yijing@email.com', '013-3752221', 'Active', 'chai'),
(3, 'F', 'efg123', 'KESSIGAN A/L THIRUNAVUKKARASU', 'Plaza Ampang City Jln Ampang', 'kessigan@email.com', '019-6542221', 'Active', 'kessigan'),
(4, 'F', 'hij112', 'LISHA ROSHINEE A/P GANESAN', '7 Lorong Mewah 11 Bandar Tun Razak Cheras', 'lisha@email.com', '014-655 2885 \n', 'Active', 'lisha'),
(5, 'M', '2313u', 'NATALIE TAN WEI MEI', ' A 17 Lrg Bunga Matahari 1B Taman Maju Jaya', 'weimei@email.com', '016-0411904', 'Active', 'natalie'),
(7, 'F', '4j31', 'SNEHA SEENIVASAN', 'Taman Industri Alam Jaya, Bandar Puncak Alam,  Shah Alam', 'sneha@email.com', '013-5379904', 'Active', 'sneha'),
(8, 'F', '33nj', 'OOI XIEN XIEN', ' 45 Jln Ss20/11 Ss20 Petaling Jaya', 'iryn@email.com', '018-2418896', 'Active', 'ooi'),
(9, 'F', 'u4u23', 'NURAIN FARAHIN SYAZMIN', '35,jalan 1 ,Bandar Shah Alam', 'ain@email.com', '010-392 9187', 'Active', 'ain'),
(10, 'F', 'abc123', 'siti', 'No7,jalan2, taman bahagia, 45500 tanjong karang,\nselangor', 'siti@gmail.com', '0178899567', 'Active', 'siti'),
(11, 'M', 'abc123', 'abu', 'No4,jalan2,taman ria,45500 tanjong karang,selangor', 'abu@gmail.com', '0178899123', 'Active', 'abu');

-- --------------------------------------------------------

--
-- Table structure for table `flight`
--

CREATE TABLE `flight` (
  `Flight_ID` int(10) NOT NULL,
  `Flight_Name` varchar(50) NOT NULL,
  `Airline_ID` int(10) DEFAULT NULL,
  `Destination` varchar(100) NOT NULL,
  `Departure_Time` datetime NOT NULL,
  `Arrival_Time` datetime NOT NULL,
  `Departure_airport` varchar(100) NOT NULL,
  `Arrival_airport` varchar(100) NOT NULL,
  `defaultBaggage` int(11) NOT NULL,
  `Status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `flight`
--

INSERT INTO `flight` (`Flight_ID`, `Flight_Name`, `Airline_ID`, `Destination`, `Departure_Time`, `Arrival_Time`, `Departure_airport`, `Arrival_airport`, `defaultBaggage`, `Status`) VALUES
(1, 'Emirates 101', 1, 'New York', '2025-02-08 10:00:00', '2025-02-10 22:00:00', 'Kuala Lumpur International Airport (KLIA)', 'JFK International', 5, 'Scheduled'),
(2, 'British Airways 202', 2, 'London', '2025-02-09 08:30:00', '2025-02-11 14:30:00', 'Kuala Lumpur International Airport (KLIA)', 'Heathrow', 7, 'Scheduled'),
(3, 'Air France 303', 3, 'Paris', '2025-02-10 14:15:00', '2025-02-12 20:15:00', 'Kuala Lumpur International Airport (KLIA)', 'Charles de Gaulle', 7, 'Scheduled'),
(4, 'Lufthansa 404', 4, 'Tokyo', '2025-02-13 23:45:00', '2025-02-15 15:45:00', 'Kuala Lumpur International Airport (KLIA)', 'Narita International', 7, 'Scheduled'),
(5, 'Singapore Airlines 505', 5, 'Singapore', '2025-02-14 16:20:00', '2025-02-15 08:20:00', 'Kuala Lumpur International Airport (KLIA)', 'Changi', 5, 'Scheduled');

-- --------------------------------------------------------

--
-- Table structure for table `seats`
--

CREATE TABLE `seats` (
  `Seat_ID` int(10) NOT NULL,
  `Flight_ID` int(10) NOT NULL,
  `Seat_No` varchar(10) NOT NULL,
  `Seat_Class` varchar(20) DEFAULT NULL,
  `Price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seats`
--

INSERT INTO `seats` (`Seat_ID`, `Flight_ID`, `Seat_No`, `Seat_Class`, `Price`) VALUES
(1, 1, 'A1', 'Economy', 1500.00),
(2, 1, 'B1', 'Economy', 1500.00),
(3, 1, 'C1', 'Economy', 1500.00),
(4, 1, 'D1', 'Economy', 1500.00),
(5, 1, 'E1', 'Economy', 1500.00),
(6, 1, 'A2', 'Economy', 1500.00),
(7, 1, 'B2', 'Economy', 1500.00),
(8, 1, 'C2', 'Economy', 1500.00),
(9, 1, 'D2', 'Economy', 1500.00),
(10, 1, 'E2', 'Economy', 1500.00),
(11, 1, 'A3', 'Premium Economy', 2000.00),
(12, 1, 'B3', 'Premium Economy', 2000.00),
(13, 1, 'C3', 'Premium Economy', 2000.00),
(14, 1, 'D3', 'Premium Economy', 2000.00),
(15, 1, 'E3', 'Premium Economy', 2000.00),
(16, 1, 'A4', 'Business', 3000.00),
(17, 1, 'B4', 'Business', 3000.00),
(18, 1, 'C4', 'Business', 3000.00),
(19, 1, 'D4', 'Business', 3000.00),
(20, 1, 'E4', 'Business', 3000.00),
(21, 1, 'A5', 'First Class', 5000.00),
(22, 1, 'B5', 'First Class', 5000.00),
(23, 1, 'C5', 'First Class', 5000.00),
(24, 1, 'D5', 'First Class', 5000.00),
(25, 1, 'E5', 'First Class', 5000.00),
(26, 2, 'A1', 'Economy', 1500.00),
(27, 2, 'B1', 'Economy', 1500.00),
(28, 2, 'C1', 'Economy', 1500.00),
(29, 2, 'D1', 'Economy', 1500.00),
(30, 2, 'E1', 'Economy', 1500.00),
(31, 2, 'A2', 'Economy', 1500.00),
(32, 2, 'B2', 'Economy', 1500.00),
(33, 2, 'C2', 'Economy', 1500.00),
(34, 2, 'D2', 'Economy', 1500.00),
(35, 2, 'E2', 'Economy', 1500.00),
(36, 2, 'A3', 'Premium Economy', 2000.00),
(37, 2, 'B3', 'Premium Economy', 2000.00),
(38, 2, 'C3', 'Premium Economy', 2000.00),
(39, 2, 'D3', 'Premium Economy', 2000.00),
(40, 2, 'E3', 'Premium Economy', 2000.00),
(41, 2, 'A4', 'Business', 3000.00),
(42, 2, 'B4', 'Business', 3000.00),
(43, 2, 'C4', 'Business', 3000.00),
(44, 2, 'D4', 'Business', 3000.00),
(45, 2, 'E4', 'Business', 3000.00),
(46, 2, 'A5', 'First Class', 5000.00),
(47, 2, 'B5', 'First Class', 5000.00),
(48, 2, 'C5', 'First Class', 5000.00),
(49, 2, 'D5', 'First Class', 5000.00),
(50, 2, 'E5', 'First Class', 5000.00),
(51, 3, 'A1', 'Economy', 1500.00),
(52, 3, 'B1', 'Economy', 1500.00),
(53, 3, 'C1', 'Economy', 1500.00),
(54, 3, 'D1', 'Economy', 1500.00),
(55, 3, 'E1', 'Economy', 1500.00),
(56, 3, 'A2', 'Economy', 1500.00),
(57, 3, 'B2', 'Economy', 1500.00),
(58, 3, 'C2', 'Economy', 1500.00),
(59, 3, 'D2', 'Economy', 1500.00),
(60, 3, 'E2', 'Economy', 1500.00),
(61, 3, 'A3', 'Premium Economy', 2000.00),
(62, 3, 'B3', 'Premium Economy', 2000.00),
(63, 3, 'C3', 'Premium Economy', 2000.00),
(64, 3, 'D3', 'Premium Economy', 2000.00),
(65, 3, 'E3', 'Premium Economy', 2000.00),
(66, 3, 'A4', 'Business', 3000.00),
(67, 3, 'B4', 'Business', 3000.00),
(68, 3, 'C4', 'Business', 3000.00),
(69, 3, 'D4', 'Business', 3000.00),
(70, 3, 'E4', 'Business', 3000.00),
(71, 3, 'A5', 'First Class', 5000.00),
(72, 3, 'B5', 'First Class', 5000.00),
(73, 3, 'C5', 'First Class', 5000.00),
(74, 3, 'D5', 'First Class', 5000.00),
(75, 3, 'E5', 'First Class', 5000.00),
(76, 4, 'A1', 'Economy', 1500.00),
(77, 4, 'B1', 'Economy', 1500.00),
(78, 4, 'C1', 'Economy', 1500.00),
(79, 4, 'D1', 'Economy', 1500.00),
(80, 4, 'E1', 'Economy', 1500.00),
(81, 4, 'A2', 'Economy', 1500.00),
(82, 4, 'B2', 'Economy', 1500.00),
(83, 4, 'C2', 'Economy', 1500.00),
(84, 4, 'D2', 'Economy', 1500.00),
(85, 4, 'E2', 'Economy', 1500.00),
(86, 4, 'A3', 'Premium Economy', 2000.00),
(87, 4, 'B3', 'Premium Economy', 2000.00),
(88, 4, 'C3', 'Premium Economy', 2000.00),
(89, 4, 'D3', 'Premium Economy', 2000.00),
(90, 4, 'E3', 'Premium Economy', 2000.00),
(91, 4, 'A4', 'Business', 3000.00),
(92, 4, 'B4', 'Business', 3000.00),
(93, 4, 'C4', 'Business', 3000.00),
(94, 4, 'D4', 'Business', 3000.00),
(95, 4, 'E4', 'Business', 3000.00),
(96, 4, 'A5', 'First Class', 5000.00),
(97, 4, 'B5', 'First Class', 5000.00),
(98, 4, 'C5', 'First Class', 5000.00),
(99, 4, 'D5', 'First Class', 5000.00),
(100, 4, 'E5', 'First Class', 5000.00),
(101, 5, 'A1', 'Economy', 1500.00),
(102, 5, 'B1', 'Economy', 1500.00),
(103, 5, 'C1', 'Economy', 1500.00),
(104, 5, 'D1', 'Economy', 1500.00),
(105, 5, 'E1', 'Economy', 1500.00),
(106, 5, 'A2', 'Economy', 1500.00),
(107, 5, 'B2', 'Economy', 1500.00),
(108, 5, 'C2', 'Economy', 1500.00),
(109, 5, 'D2', 'Economy', 1500.00),
(110, 5, 'E2', 'Economy', 1500.00),
(111, 5, 'A3', 'Premium Economy', 2000.00),
(112, 5, 'B3', 'Premium Economy', 2000.00),
(113, 5, 'C3', 'Premium Economy', 2000.00),
(114, 5, 'D3', 'Premium Economy', 2000.00),
(115, 5, 'E3', 'Premium Economy', 2000.00),
(116, 5, 'A4', 'Business', 3000.00),
(117, 5, 'B4', 'Business', 3000.00),
(118, 5, 'C4', 'Business', 3000.00),
(119, 5, 'D4', 'Business', 3000.00),
(120, 5, 'E4', 'Business', 3000.00),
(121, 5, 'A5', 'First Class', 5000.00),
(122, 5, 'B5', 'First Class', 5000.00),
(123, 5, 'C5', 'First Class', 5000.00),
(124, 5, 'D5', 'First Class', 5000.00),
(125, 5, 'E5', 'First Class', 5000.00);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `airlines`
--
ALTER TABLE `airlines`
  ADD PRIMARY KEY (`Airline_ID`);

--
-- Indexes for table `baggage`
--
ALTER TABLE `baggage`
  ADD PRIMARY KEY (`baggageID`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`Seat_ID`,`Flight_ID`),
  ADD KEY `Flight_ID` (`Flight_ID`),
  ADD KEY `bookingfk4` (`baggageID`),
  ADD KEY `booking_ibfk_3` (`Customer_ID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`Customer_ID`),
  ADD UNIQUE KEY `customer_email` (`customer_email`);

--
-- Indexes for table `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`Flight_ID`),
  ADD KEY `Airline_ID` (`Airline_ID`);

--
-- Indexes for table `seats`
--
ALTER TABLE `seats`
  ADD PRIMARY KEY (`Seat_ID`),
  ADD KEY `seatsfk1` (`Flight_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `airlines`
--
ALTER TABLE `airlines`
  MODIFY `Airline_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `baggage`
--
ALTER TABLE `baggage`
  MODIFY `baggageID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `Customer_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `flight`
--
ALTER TABLE `flight`
  MODIFY `Flight_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`Seat_ID`) REFERENCES `seats` (`Seat_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`Flight_ID`) REFERENCES `flight` (`Flight_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `booking_ibfk_3` FOREIGN KEY (`Customer_ID`) REFERENCES `customer` (`Customer_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bookingfk4` FOREIGN KEY (`baggageID`) REFERENCES `baggage` (`baggageID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `flight`
--
ALTER TABLE `flight`
  ADD CONSTRAINT `flight_ibfk_1` FOREIGN KEY (`Airline_ID`) REFERENCES `airlines` (`Airline_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `seats`
--
ALTER TABLE `seats`
  ADD CONSTRAINT `seatsfk1` FOREIGN KEY (`Flight_ID`) REFERENCES `flight` (`Flight_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `auto_delete_expired_bookings` ON SCHEDULE EVERY 1 MINUTE STARTS '2025-02-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM booking
WHERE deleted_at IS NOT NULL
  AND deleted_at < NOW() - INTERVAL 3 DAY$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
