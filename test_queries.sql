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
WHERE r.name = 'SELLER';