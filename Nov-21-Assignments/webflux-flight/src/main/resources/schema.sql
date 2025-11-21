DROP TABLE IF EXISTS passenger;
DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS flight;
DROP TABLE IF EXISTS airline;

CREATE TABLE airline (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  logo_url VARCHAR(500)
);


CREATE TABLE flight (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  airline_id BIGINT,
  flight_number VARCHAR(100),
  from_loc VARCHAR(255),
  to_loc VARCHAR(255),
  departure_time DATETIME,
  arrival_time DATETIME,
  price DOUBLE,
  seats_available INT
);

CREATE TABLE booking (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  pnr VARCHAR(100) UNIQUE,
  flight_id BIGINT,
  total_price DOUBLE,
  booking_time DATETIME,
  email VARCHAR(255),
  seat_count INT,
  trip_type VARCHAR(50),
  meal_preference VARCHAR(50)
);


CREATE TABLE passenger (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  email VARCHAR(255),
  gender VARCHAR(20),
  age INT,
  seat_num VARCHAR(50),
  booking_id BIGINT
);

