DROP DATABASE IF EXISTS control;
CREATE DATABASE control;
USE control;

CREATE TABLE user (
  id INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(500) NOT NULL,
  habitacion INT(100),
  token VARCHAR(500) NOT NULL
);
