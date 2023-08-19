-- Create the main database
CREATE DATABASE IF NOT EXISTS maindb;

-- Use the main database
USE maindb;

-- Create the Customers table
CREATE TABLE IF NOT EXISTS Customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    document_type VARCHAR(255) NOT NULL,
    document_number VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    cell_phone VARCHAR(255) NOT NULL
);

INSERT INTO Customers (document_type, document_number, last_name, first_name, cell_phone)
VALUES
    ('ID1', '12345', 'Doe', 'John', '123-456-7890'),
    ('ID2', '67890', 'Smith', 'Jane', '987-654-3210'),
    ('ID3', '54321', 'Johnson', 'Robert', '555-555-5555'),
    ('ID4', '98765', 'Williams', 'Emily', '111-222-3333'),
    ('ID5', '12345', 'Brown', 'Michael', '444-444-4444'),
    ('ID6', '67890', 'Davis', 'Jessica', '999-888-7777'),
    ('ID7', '54321', 'Miller', 'Sarah', '666-666-6666'),
    ('ID8', '98765', 'Wilson', 'David', '222-111-0000'),
    ('ID9', '12345', 'Taylor', 'Linda', '777-777-7777'),
    ('ID10', '67890', 'Anderson', 'Daniel', '555-666-7777'),
    ('ID11', '54321', 'Thomas', 'Maria', '333-222-1111'),
    ('ID12', '98765', 'Martinez', 'Chris', '111-222-3333');