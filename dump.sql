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

CREATE TABLE financial_product_kind (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type ENUM('CLASSIC_CREDIT_CARD', 'GOLD_CREDIT_CARD', 'PLATINUM_CREDIT_CARD', 'MORTGAGE', 'PERSONAL_LOAN') NOT NULL,
    points_per_amount DOUBLE
);

CREATE TABLE FinancialProducts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    kind_id INT NOT NULL,
    FOREIGN KEY (kind_id) REFERENCES financial_product_kind(id)
);

CREATE TABLE Persons (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    document_type ENUM('DNI', 'PASSPORT', 'OTHER'),
    document_number VARCHAR(255),
    last_name VARCHAR(255),
    first_name VARCHAR(255),
    cell_phone_number VARCHAR(255)
);


CREATE TABLE Roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
);

CREATE TABLE Employees (
    id BIGINT PRIMARY KEY,
    role_id BIGINT,
    manager_id BIGINT,
    FOREIGN KEY (id) REFERENCES Persons(id),
    FOREIGN KEY (role_id) REFERENCES Roles(id),
    FOREIGN KEY (manager_id) REFERENCES Employees(id) -- Assuming manager is a role
);

CREATE TABLE Sales (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    seller_id BIGINT,
    -- Additional columns for sale-specific attributes
    FOREIGN KEY (seller_id) REFERENCES Roles(id) -- Assuming seller is a role
);

-- Additional constraints and indexes as needed



-- CREATE TABLE ManagerEmployee (
--     manager_id BIGINT,
--     employee_id BIGINT,
--     PRIMARY KEY (manager_id, employee_id),
--     FOREIGN KEY (manager_id) REFERENCES Employee(id),
--     FOREIGN KEY (employee_id) REFERENCES Employee(id)
-- );

-- DATA LOADING 

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

INSERT INTO financial_product_kind (type, points_per_amount) VALUES ('CLASSIC_CREDIT_CARD', 1.0);
INSERT INTO financial_product_kind (type, points_per_amount) VALUES ('GOLD_CREDIT_CARD', 1.5);
INSERT INTO financial_product_kind (type, points_per_amount) VALUES ('PLATINUM_CREDIT_CARD', 2.0);
INSERT INTO financial_product_kind (type, points_per_amount) VALUES ('MORTGAGE', 3.0);
INSERT INTO financial_product_kind (type, points_per_amount) VALUES ('PERSONAL_LOAN', 2.5);

INSERT INTO FinancialProducts (name, kind_id) VALUES ('Classic Card', 1);
INSERT INTO FinancialProducts (name, kind_id) VALUES ('Gold Card', 2);
INSERT INTO FinancialProducts (name, kind_id) VALUES ('Platinum Card', 3);
INSERT INTO FinancialProducts (name, kind_id) VALUES ('Home Mortgage', 4);
INSERT INTO FinancialProducts (name, kind_id) VALUES ('Personal Loan', 5);
INSERT INTO FinancialProducts (name, kind_id) VALUES ('Premium Gold Card', 2);
INSERT INTO FinancialProducts (name, kind_id) VALUES ('Premium Platinum Card', 3);
