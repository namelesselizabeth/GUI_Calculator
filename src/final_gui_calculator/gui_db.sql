CREATE DATABASE guicalc;

USE guicalc;

CREATE TABLE calculation_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_number VARCHAR(255),
    operator VARCHAR(5),
    second_number VARCHAR(255),
    result VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
