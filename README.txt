Banking Application
This is a simple Spring Boot application for managing banking operations such as transferring money between accounts, creating accounts, depositing and withdrawing money, and viewing transaction history.

The application will start running on http://localhost:8080.

API Endpoints
The following are the available API endpoints:

POST /accounts/create: Create a new bank account.
GET /accounts/{id}: Get details of a specific account by ID.
GET /accounts: Get a list of all bank accounts.
GET /accounts/{id}/balance : Get the balance of targeted account.
GET /accounts/{id}/deposit: Deposit money to the targeted account.
GET /accounts/{id}/withdraw: Withdraw money from the targeted account.
POST /transactions/transfer: Transfer money between accounts.
GET /transactions: Get list of all transactions
GET /bank/totalTransactionFee: Get the total transaction fee amount.
GET /bank/totalTransferAmount: Get the total transfer amount.