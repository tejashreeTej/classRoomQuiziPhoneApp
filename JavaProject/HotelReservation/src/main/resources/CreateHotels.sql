DROP Database IF EXISTS `TestHotel`; 

CREATE DATABASE `TestHotel` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `Hotel` (
  `HotelId` int(11) NOT NULL AUTO_INCREMENT,
  `HotelName` varchar(45) NOT NULL,
  `Hotel_city` varchar(45) DEFAULT NULL,
  `HotelAvailable` int(11) DEFAULT NULL,
  PRIMARY KEY (`HotelId`),
  UNIQUE KEY `HotelId_UNIQUE` (`HotelId`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=latin1;

INSERT INTO `TestHotel`.`Hotel`
(`HotelId`,
`HotelName`,
`Hotel_city`,
`HotelAvailable`)
VALUES(1,'Monte Carlo','Fremont',1);
INSERT INTO `TestHotel`.`Hotel`
(`HotelId`,
`HotelName`,
`Hotel_city`,
`HotelAvailable`)
VALUES(2,'Taj hotel','Fremont',1);
INSERT INTO `TestHotel`.`Hotel`
(`HotelId`,
`HotelName`,
`Hotel_city`,
`HotelAvailable`)
VALUES(3,'Hilton Hotel','San Jose',1);
INSERT INTO `TestHotel`.`Hotel`
(`HotelId`,
`HotelName`,
`Hotel_city`,
`HotelAvailable`)
VALUES(4,'Hyatt Hotels','Sunnyvale',1);


CREATE TABLE `Rooms` (
  `RoomId` int(11) NOT NULL AUTO_INCREMENT,
  `RoomType` varchar(45) NOT NULL,
  `TotalRoom` int(11) DEFAULT NULL,
  `AvailableRooms` int(11) DEFAULT NULL,
  `RoomCost` decimal(10,0) DEFAULT NULL,
  `HotelId` int(11) DEFAULT NULL,
  PRIMARY KEY (`RoomId`),
  KEY `FKHotelId_idx` (`HotelId`),
  CONSTRAINT `FKHotelId` FOREIGN KEY (`HotelId`) REFERENCES `Hotel` (`HotelId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=latin1;

INSERT INTO `TestHotel`.`Rooms`
(`RoomId`,
`RoomType`,
`TotalRoom`,
`AvailableRooms`,
`RoomCost`,
`HotelId`)
VALUES
(1,'Delux Rooms',200,150,180,1);
INSERT INTO `TestHotel`.`Rooms`
(`RoomId`,
`RoomType`,
`TotalRoom`,
`AvailableRooms`,
`RoomCost`,
`HotelId`)
VALUES
(2,'Suits',150,100,190,1);

INSERT INTO `TestHotel`.`Rooms`
(`RoomId`,
`RoomType`,
`TotalRoom`,
`AvailableRooms`,
`RoomCost`,
`HotelId`)
VALUES
(3,'Premium Rooms',150,110,185,2);
INSERT INTO `TestHotel`.`Rooms`
(`RoomId`,
`RoomType`,
`TotalRoom`,
`AvailableRooms`,
`RoomCost`,
`HotelId`)
VALUES
(4,'Delux Rooms',150,115,200,2);


CREATE TABLE `Customer` (
  `CustId` int(11) NOT NULL AUTO_INCREMENT,
  `Cust_First_Name` varchar(45) DEFAULT NULL,
  `Cust_Last_Name` varchar(45) DEFAULT NULL,
  `HotelId` int(11) DEFAULT NULL,
  `RoomId` int(11) DEFAULT NULL,
  `TotalCost` double DEFAULT NULL,
  `noOfNights` int(11) DEFAULT '1',
  PRIMARY KEY (`CustId`),
  KEY `FK_CustHotelId_idx` (`HotelId`),
  KEY `FK_RoomId_idx` (`RoomId`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=latin1;

INSERT INTO `TestHotel`.`Customer` (`CustId`, `Cust_First_Name`, `Cust_Last_Name`, `HotelId`, `RoomId`, `TotalCost`, `noOfNights`) VALUES ('100', 'Tejashree', 'Jagtap', '1', '2', '390', '2');
INSERT INTO `TestHotel`.`Customer` (`CustId`, `Cust_First_Name`, `Cust_Last_Name`, `HotelId`, `RoomId`, `TotalCost`, `noOfNights`) VALUES ('101', 'Shubham', 'Jagtap', '1', '1', '370', '2');
INSERT INTO `TestHotel`.`Customer` (`CustId`, `Cust_First_Name`, `Cust_Last_Name`, `HotelId`, `RoomId`, `TotalCost`, `noOfNights`) VALUES ('102', 'Divya', 'Jagtap', '2', '3', '190', '1');




