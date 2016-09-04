CREATE USER 'hotel_user'@'localhost' IDENTIFIED BY 'myhotel';

GRANT ALL PRIVILEGES ON TestHotel.* TO 'hotel_user'@'localhost' WITH GRANT OPTION;

SHOW GRANTS FOR 'hotel_user'@'localhost';