SELECT * FROM maindb.Roles;
use maindb;

SELECT *
FROM Employees e
INNER JOIN Persons p on (e.person_id = p.id)
INNER JOIN maindb.Roles r on (e.role_id = r.id)
LEFT OUTER JOIN maindb.MonthlyGoals mg on (mg.employee_id = e.id);

SELECT *
FROM Employees e
INNER JOIN Persons p on (e.person_id = p.id)
INNER JOIN maindb.Roles r on (e.role_id = r.id)
LEFT OUTER JOIN maindb.MonthlyGoals mg on (mg.employee_id = e.id)
WHERE r.name = 'SELLER';

SELECT *
FROM Employees e
INNER JOIN Persons p on (e.person_id = p.id)
INNER JOIN maindb.Roles r on (e.role_id = r.id)
LEFT OUTER JOIN maindb.MonthlyGoals mg on (mg.employee_id = e.id)
WHERE r.name = 'MANAGER';

SELECT *
FROM Customers c
INNER JOIN Persons p on (c.person_id = p.id);

SELECT *
FROM Employees e
INNER JOIN Persons p on (e.person_id = p.id)
INNER JOIN maindb.Roles r on (e.role_id = r.id)
INNER JOIN maindb.Sales s on (s.seller_id = e.id)
LEFT OUTER JOIN maindb.MonthlyGoals mg on (mg.employee_id = e.id)
WHERE r.name = 'SELLER';

