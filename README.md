
# Bank Sale-Reward System



Prototype application for a bank reward system for sales

Domain Scope: Sales 



## Project Information
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

![Java JDK Version](https://img.shields.io/badge/Java%20JDK-17%2B-blue.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)


## Proposed End-points

### Authentication

- `POST /api/auth/login`: Authenticate a user and return a JWT token.

### Managers

- `GET /api/managers`: Get a list of all managers.
- `GET /api/managers/{id}`: Get details of a specific manager.
- `POST /api/managers`: Create a new manager.
- `PUT /api/managers/{id}`: Update a manager's details.
- `DELETE /api/managers/{id}`: Delete a manager.

### Sales Advisors (Asesores Comerciales) (I named this ones as 'Sellers' in the code)

- `GET /api/advisors`: Get a list of all advisors.
- `GET /api/advisors/{id}`: Get details of a specific advisor.
- `POST /api/advisors`: Create a new advisor.
- `PUT /api/advisors/{id}`: Update an advisor's details.
- `DELETE /api/advisors/{id}`: Delete an advisor.

### Sales Goals

- `GET /api/goals`: Get a list of all sales goals.
- `GET /api/goals/{id}`: Get details of a specific sales goal.
- `POST /api/goals`: Set a new sales goal for an advisor.
- `PUT /api/goals/{id}`: Update a sales goal.
- `DELETE /api/goals/{id}`: Delete a sales goal.

### Financial Products

- `GET /api/products`: Get a list of all financial products.
- `GET /api/products/{id}`: Get details of a specific financial product.
- `POST /api/products`: Create a new financial product.
- `PUT /api/products/{id}`: Update a financial product.
- `DELETE /api/products/{id}`: Delete a financial product.

### Sales

- `GET /api/sales`: Get a list of all sales.
- `GET /api/sales/{id}`: Get details of a specific sale.
- `POST /api/sales`: Record a new sale.
- `PUT /api/sales/{id}`: Update a sale's details.
- `DELETE /api/sales/{id}`: Delete a sale.

### Clients

- `GET /api/clients`: Get a list of all clients.
- `GET /api/clients/{id}`: Get details of a specific client.
- `POST /api/clients`: Create a new client.
- `PUT /api/clients/{id}`: Update a client's details.
- `DELETE /api/clients/{id}`: Delete a client.

### Reports

- `GET /api/reports/monthly`: Get a monthly report of sales and goals.
- `GET /api/reports/advisor/{id}`: Get a report for a specific advisor.

## Authors

- Rodriguez Perez Rommel [@rommel-rodriguezperez](https://github.com/rommel-rodriguezperez)

## 🔗 Links


## License

This project is licensed under the [MIT License](./LICENSE).
