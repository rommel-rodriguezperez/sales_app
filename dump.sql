-- Create the main database
CREATE DATABASE IF NOT EXISTS maindb;

-- Use the main database
USE maindb;


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
    cell_phone_number VARCHAR(255),
    active BOOLEAN DEFAULT TRUE
);

-- Create the Customers table
CREATE TABLE IF NOT EXISTS Customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    person_id BIGINT,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE Roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE Employees (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    person_id BIGINT,
    role_id BIGINT,
    manager_id BIGINT,
    active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id) REFERENCES Persons(id),
    FOREIGN KEY (role_id) REFERENCES Roles(id),
    FOREIGN KEY (manager_id) REFERENCES Employees(id) -- Assuming manager is a role
);


CREATE TABLE Sales (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    seller_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    date DATE,
    FOREIGN KEY (seller_id) REFERENCES Employees(id),
    FOREIGN KEY (customer_id) REFERENCES Customers(id)
);

CREATE TABLE SalesDetails (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sale_id BIGINT,
    product_id BIGINT NOT NULL,
    amount DECIMAL(10, 2),
    FOREIGN KEY (sale_id) REFERENCES Sales(id),
    FOREIGN KEY (product_id) REFERENCES FinancialProducts(id)
);

CREATE TABLE MonthlyGoals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    month INT NOT NULL,
    year INT NOT NULL,
    goal_amount DOUBLE NOT NULL,
    UNIQUE (employee_id, month, year),
    FOREIGN KEY (employee_id) REFERENCES Employees(id)
);

CREATE TABLE ApplicationRoles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE ApplicationUsers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id BIGINT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT,
    active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (employee_id) REFERENCES Employees(id),
    FOREIGN KEY (role_id) REFERENCES ApplicationRoles(id)
);


-- DATA LOADING 

-- INSERT INTO Customers (document_type, document_number, last_name, first_name, cell_phone)
-- VALUES
--     ('ID1', '12345', 'Doe', 'John', '123-456-7890'),
--     ('ID2', '67890', 'Smith', 'Jane', '987-654-3210'),
--     ('ID3', '54321', 'Johnson', 'Robert', '555-555-5555'),
--     ('ID4', '98765', 'Williams', 'Emily', '111-222-3333'),
--     ('ID5', '12345', 'Brown', 'Michael', '444-444-4444'),
--     ('ID6', '67890', 'Davis', 'Jessica', '999-888-7777'),
--     ('ID7', '54321', 'Miller', 'Sarah', '666-666-6666'),
--     ('ID8', '98765', 'Wilson', 'David', '222-111-0000'),
--     ('ID9', '12345', 'Taylor', 'Linda', '777-777-7777'),
--     ('ID10', '67890', 'Anderson', 'Daniel', '555-666-7777'),
--     ('ID11', '54321', 'Thomas', 'Maria', '333-222-1111'),
--     ('ID12', '98765', 'Martinez', 'Chris', '111-222-3333');

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

-- Employee's Roles
INSERT INTO Roles (name) VALUES
('MANAGER'),
('SELLER');

-- Persons that are employees
INSERT INTO Persons (document_type, document_number, last_name, first_name, cell_phone_number, active)
VALUES
('DNI', '12345678', 'Garcia', 'Juan', '987654321', TRUE),
('PASSPORT', 'P1234567', 'Perez', 'Maria', '987654322', TRUE),
('OTHER', 'O123456', 'Lopez', 'Carlos', '987654323', TRUE),
('DNI', '45678901', 'Sanchez', 'Ana', '987654324', TRUE),
('PASSPORT', 'P7654321', 'Ramirez', 'Pedro', '987654325', TRUE),
('DNI', '67890123', 'Gonzalez', 'Sofia', '987654326', TRUE),
('OTHER', 'O789012', 'Martinez', 'Luis', '987654327', TRUE),
('DNI', '89012345', 'Rodriguez', 'Elena', '987654328', TRUE),
('PASSPORT', 'P9012345', 'Hernandez', 'Diego', '987654329', TRUE),
('DNI', '01234567', 'Torres', 'Isabel', '987654330', TRUE);

-- Linking to employees
INSERT INTO Employees (person_id, role_id, manager_id, active)
VALUES
(1, 1, NULL, TRUE),  -- Juan is a manager
(2, 1, NULL, TRUE),  -- Maria is also a manager
(3, 2, 1, TRUE),      -- Carlos is a seller under Juan
(4, 2, 1, TRUE),      -- Ana is a seller under Juan
(5, 2, 1, TRUE),      -- Pedro is a seller under Juan
(6, 2, 1, TRUE),      -- Sofia is a seller under Juan
(7, 2, 2, TRUE),      -- Luis is a seller under Maria
(8, 2, 2, TRUE),      -- Elena is a seller under Maria
(9, 2, 2, TRUE),      -- Diego is a seller under Maria
(10, 2, 2, TRUE);     -- Isabel is a seller under Maria

-- Persons who will be customers
INSERT INTO Persons (document_type, document_number, last_name, first_name, cell_phone_number, active)
VALUES
('DNI', '11223344', 'Alvarez', 'Fernando', '987654331', TRUE),
('PASSPORT', 'P2233445', 'Vasquez', 'Carmen', '987654332', TRUE),
('OTHER', 'O334455', 'Munoz', 'Ricardo', '987654333', TRUE),
('DNI', '44556677', 'Ortiz', 'Laura', '987654334', TRUE),
('PASSPORT', 'P5566778', 'Romero', 'Sergio', '987654335', TRUE),
('DNI', '66778899', 'Medina', 'Claudia', '987654336', TRUE),
('OTHER', 'O778899', 'Gutierrez', 'Alberto', '987654337', TRUE),
('DNI', '88990011', 'Cruz', 'Sandra', '987654338', TRUE),
('PASSPORT', 'P9900112', 'Castillo', 'Victor', '987654339', TRUE),
('DNI', '00112233', 'Jimenez', 'Monica', '987654340', TRUE),
('OTHER', 'O112233', 'Ruiz', 'Adrian', '987654341', TRUE),
('DNI', '22334455', 'Soto', 'Daniela', '987654342', TRUE);

-- Linking to Customers table
INSERT INTO Customers (person_id, active)
VALUES
(11, TRUE),  -- Fernando
(12, TRUE),  -- Carmen
(13, TRUE),  -- Ricardo
(14, TRUE),  -- Laura
(15, TRUE),  -- Sergio
(16, TRUE),  -- Claudia
(17, TRUE),  -- Alberto
(18, TRUE),  -- Sandra
(19, TRUE),  -- Victor
(20, TRUE),  -- Monica
(21, TRUE),  -- Adrian
(22, TRUE);  -- Daniela

-- -- Inserting Sales
-- INSERT INTO Sales (seller_id, customer_id, date)
-- VALUES
-- (2, 11, '2023-08-01'),  -- Maria sold to Fernando
-- (3, 12, '2023-08-02'),  -- Carlos sold to Carmen
-- (4, 13, '2023-08-03'),  -- Ana sold to Ricardo
-- (5, 14, '2023-08-04'),  -- Pedro sold to Laura
-- (6, 15, '2023-08-05'),  -- Sofia sold to Sergio
-- (7, 16, '2023-08-06'),  -- Luis sold to Claudia
-- (8, 17, '2023-08-07'),  -- Elena sold to Alberto
-- (9, 18, '2023-08-08'),  -- Diego sold to Sandra
-- (10, 19, '2023-08-09'), -- Isabel sold to Victor
-- (2, 20, '2023-08-10'),  -- Maria sold to Monica
-- (3, 21, '2023-08-11'),  -- Carlos sold to Adrian
-- (4, 22, '2023-08-12'),  -- Ana sold to Daniela
-- (5, 11, '2023-08-13'),  -- Pedro sold to Fernando
-- (6, 12, '2023-08-14'),  -- Sofia sold to Carmen
-- (7, 13, '2023-08-15'),  -- Luis sold to Ricardo
-- (8, 14, '2023-08-16'),  -- Elena sold to Laura
-- (9, 15, '2023-08-17'),  -- Diego sold to Sergio
-- (10, 16, '2023-08-18'), -- Isabel sold to Claudia
-- (2, 17, '2023-08-19'),  -- Maria sold to Alberto
-- (3, 18, '2023-08-20');  -- Carlos sold to Sandra

-- Inserting Sales
INSERT INTO Sales (seller_id, customer_id, date)
VALUES
(3, 1, '2023-08-01'),  
(3, 2, '2023-08-02'),  -- Carlos sold to Carmen
(4, 3, '2023-08-03'),  -- Ana sold to Ricardo
(5, 4, '2023-08-04'),  -- Pedro sold to Laura
(6, 5, '2023-08-05'),  -- Sofia sold to Sergio
(7, 6, '2023-08-06'),  -- Luis sold to Claudia
(8, 7, '2023-08-07'),  -- Elena sold to Alberto
(9, 8, '2023-08-08'),  -- Diego sold to Sandra
(10, 9, '2023-08-09'), -- Isabel sold to Victor
(4, 10, '2023-08-10'), -- Maria sold to Monica
(3, 11, '2023-08-11'), -- Carlos sold to Adrian
(4, 12, '2023-08-12'), -- Ana sold to Daniela
(5, 1, '2023-08-13'),  -- Pedro sold to Fernando
(6, 2, '2023-08-14'),  -- Sofia sold to Carmen
(7, 3, '2023-08-15'),  -- Luis sold to Ricardo
(8, 4, '2023-08-16'),  -- Elena sold to Laura
(9, 5, '2023-08-17'),  -- Diego sold to Sergio
(10, 6, '2023-08-18'), -- Isabel sold to Claudia
(4, 7, '2023-08-19'),  
(3, 8, '2023-08-20');  -- Carlos sold to Sandra

-- -- Inserting SalesDetails
-- INSERT INTO SalesDetails (sale_id, product_id, amount)
-- VALUES
-- (1, 1, 1.00),     -- Maria sold CLASSIC_CREDIT_CARD
-- (1, 2, 1.00),     -- Maria sold GOLD_CREDIT_CARD
-- (2, 3, 1.00),     -- Carlos sold PLATINUM_CREDIT_CARD
-- (2, 4, 80000.00), -- Carlos sold MORTGAGE
-- (3, 5, 9000.00),  -- Ana sold PERSONAL_LOAN
-- (4, 1, 1.00),     -- Pedro sold CLASSIC_CREDIT_CARD
-- (4, 2, 1.00),     -- Pedro sold GOLD_CREDIT_CARD
-- (5, 3, 1.00),     -- Sofia sold PLATINUM_CREDIT_CARD
-- (5, 4, 85000.00), -- Sofia sold MORTGAGE
-- (6, 5, 7000.00),  -- Luis sold PERSONAL_LOAN
-- (7, 1, 1.00),     -- Elena sold CLASSIC_CREDIT_CARD
-- (7, 2, 1.00),     -- Elena sold GOLD_CREDIT_CARD
-- (8, 3, 1.00),     -- Diego sold PLATINUM_CREDIT_CARD
-- (8, 4, 75000.00), -- Diego sold MORTGAGE
-- (9, 5, 6000.00),  -- Isabel sold PERSONAL_LOAN
-- (10, 1, 1.00),    -- Maria sold CLASSIC_CREDIT_CARD
-- (10, 2, 1.00),    -- Maria sold GOLD_CREDIT_CARD
-- (11, 3, 1.00),    -- Carlos sold PLATINUM_CREDIT_CARD
-- (11, 4, 90000.00),-- Carlos sold MORTGAGE
-- (12, 5, 9500.00), -- Ana sold PERSONAL_LOAN
-- (13, 1, 1.00),    -- Pedro sold CLASSIC_CREDIT_CARD
-- (13, 2, 1.00),    -- Pedro sold GOLD_CREDIT_CARD
-- (14, 3, 1.00),    -- Sofia sold PLATINUM_CREDIT_CARD
-- (14, 4, 78000.00),-- Sofia sold MORTGAGE
-- (15, 5, 7200.00), -- Luis sold PERSONAL_LOAN
-- (16, 1, 1.00),    -- Elena sold CLASSIC_CREDIT_CARD
-- (16, 2, 1.00),    -- Elena sold GOLD_CREDIT_CARD
-- (17, 3, 1.00),    -- Diego sold PLATINUM_CREDIT_CARD
-- (17, 4, 82000.00),-- Diego sold MORTGAGE
-- (18, 5, 6800.00), -- Isabel sold PERSONAL_LOAN
-- (19, 1, 1.00),    -- Maria sold CLASSIC_CREDIT_CARD
-- (19, 2, 1.00),    -- Maria sold GOLD_CREDIT_CARD
-- (20, 3, 1.00),    -- Carlos sold PLATINUM_CREDIT_CARD
-- (20, 4, 91000.00),-- Carlos sold MORTGAGE
-- (21, 5, 9600.00), -- Ana sold PERSONAL_LOAN
-- (22, 1, 1.00),    -- Pedro sold CLASSIC_CREDIT_CARD
-- (22, 2, 1.00),    -- Pedro sold GOLD_CREDIT_CARD
-- (23, 3, 1.00),    -- Sofia sold PLATINUM_CREDIT_CARD
-- (23, 4, 86000.00),-- Sofia sold MORTGAGE
-- (24, 5, 7400.00); -- Luis sold PERSONAL_LOAN

-- Inserting SalesDetails
INSERT INTO SalesDetails (sale_id, product_id, amount)
VALUES
(1, 1, 1.00),     -- Maria sold CLASSIC_CREDIT_CARD
(1, 2, 1.00),     -- Maria sold GOLD_CREDIT_CARD
(2, 3, 1.00),     -- Carlos sold PLATINUM_CREDIT_CARD
(2, 4, 80000.00), -- Carlos sold MORTGAGE
(3, 5, 9000.00),  -- Ana sold PERSONAL_LOAN
(3, 1, 1.00),     -- Ana sold CLASSIC_CREDIT_CARD
(4, 2, 1.00),     -- Pedro sold GOLD_CREDIT_CARD
(4, 3, 1.00),     -- Pedro sold PLATINUM_CREDIT_CARD
(5, 4, 85000.00), -- Sofia sold MORTGAGE
(5, 5, 7000.00),  -- Sofia sold PERSONAL_LOAN
(6, 1, 1.00),     -- Luis sold CLASSIC_CREDIT_CARD
(6, 2, 1.00),     -- Luis sold GOLD_CREDIT_CARD
(7, 3, 1.00),     -- Elena sold PLATINUM_CREDIT_CARD
(7, 4, 75000.00), -- Elena sold MORTGAGE
(8, 5, 6000.00),  -- Diego sold PERSONAL_LOAN
(8, 1, 1.00),     -- Diego sold CLASSIC_CREDIT_CARD
(9, 2, 1.00),     -- Isabel sold GOLD_CREDIT_CARD
(9, 3, 1.00),     -- Isabel sold PLATINUM_CREDIT_CARD
(10, 4, 90000.00),-- Maria sold MORTGAGE
(10, 5, 9500.00), -- Maria sold PERSONAL_LOAN
(11, 1, 1.00),    -- Carlos sold CLASSIC_CREDIT_CARD
(11, 2, 1.00),    -- Carlos sold GOLD_CREDIT_CARD
(12, 3, 1.00),    -- Ana sold PLATINUM_CREDIT_CARD
(12, 4, 78000.00),-- Ana sold MORTGAGE
(13, 5, 7200.00), -- Pedro sold PERSONAL_LOAN
(13, 1, 1.00),    -- Pedro sold CLASSIC_CREDIT_CARD
(14, 2, 1.00),    -- Sofia sold GOLD_CREDIT_CARD
(14, 3, 1.00),    -- Sofia sold PLATINUM_CREDIT_CARD
(15, 4, 82000.00),-- Luis sold MORTGAGE
(15, 5, 6800.00), -- Luis sold PERSONAL_LOAN
(16, 1, 1.00),    -- Elena sold CLASSIC_CREDIT_CARD
(16, 2, 1.00),    -- Elena sold GOLD_CREDIT_CARD
(17, 3, 1.00),    -- Diego sold PLATINUM_CREDIT_CARD
(17, 4, 91000.00),-- Diego sold MORTGAGE
(18, 5, 9600.00), -- Isabel sold PERSONAL_LOAN
(18, 1, 1.00),    -- Isabel sold CLASSIC_CREDIT_CARD
(19, 2, 1.00),    -- Maria sold GOLD_CREDIT_CARD
(19, 3, 1.00),    -- Maria sold PLATINUM_CREDIT_CARD
(20, 4, 86000.00),-- Carlos sold MORTGAGE
(20, 5, 7400.00); -- Carlos sold PERSONAL_LOAN


-- Inserting Monthly Goals for Employees
INSERT INTO MonthlyGoals (employee_id, month, year, goal_amount)
VALUES
-- Goals for Juan (Manager)
(1, 8, 2023, 50000.00),
(1, 9, 2023, 55000.00),
(1, 10, 2023, 60000.00),

-- Goals for Maria (Seller)
(2, 8, 2023, 10000.00),
(2, 9, 2023, 11000.00),
(2, 10, 2023, 12000.00),

-- Goals for Carlos (Seller)
(3, 8, 2023, 9000.00),
(3, 9, 2023, 9500.00),
(3, 10, 2023, 10000.00),

-- Goals for Ana (Seller)
(4, 8, 2023, 8000.00),
(4, 9, 2023, 8500.00),
(4, 10, 2023, 9000.00),

-- Goals for Pedro (Seller)
(5, 8, 2023, 7000.00),
(5, 9, 2023, 7500.00),
(5, 10, 2023, 8000.00),

-- Goals for Sofia (Seller)
(6, 8, 2023, 6000.00),
(6, 9, 2023, 6500.00),
(6, 10, 2023, 7000.00),

-- Goals for Luis (Seller)
(7, 8, 2023, 5000.00),
(7, 9, 2023, 5500.00),
(7, 10, 2023, 6000.00),

-- Goals for Elena (Seller)
(8, 8, 2023, 4000.00),
(8, 9, 2023, 4500.00),
(8, 10, 2023, 5000.00),

-- Goals for Diego (Seller)
(9, 8, 2023, 3000.00),
(9, 9, 2023, 3500.00),
(9, 10, 2023, 4000.00),

-- Goals for Isabel (Seller)
(10, 8, 2023, 2000.00),
(10, 9, 2023, 2500.00),
(10, 10, 2023, 3000.00);

INSERT INTO ApplicationRoles (name, description)
VALUES 
('ROLE_ADMIN', 'Has access to all functionalities and settings, including administrative panels where they can manage other users and configure settings.'),
('ROLE_MANAGER', 'Can manage user accounts and access certain administrative sections, but has limited access to sensitive settings or data.'),
('ROLE_USER', 'Can access general user functionalities and personal account settings, but does not have administrative privileges.'),
('ROLE_GUEST', 'Has limited access, usually can only view public content.'),
('ROLE_EDITOR', 'Can create and edit content, but has limited administrative privileges.'),
('ROLE_MODERATOR', 'Has some administrative privileges such as moderating user content but does not have full administrative access.'),
('ROLE_VIEWER', 'Has read-only access to certain parts of the application.'),
('ROLE_API', 'Used for API users who need to access the application’s API but may not have access to the user interface.');
