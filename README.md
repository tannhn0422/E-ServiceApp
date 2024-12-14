**Design the Classes:**
Customer: Stores customer details and account balance.
Bill: Represents a bill with attributes like ID, type, amount, due date, state, and provider.
Payment: Tracks payment transactions.
BillManagement: Handles bill-related operations (create, delete, update, search, etc.).
PaymentManagement: Manages payment operations and scheduling.

**Implement the Core Functionalities:**
Add funds to the customer's account.
Manage bills (CRUD operations).
Pay bills and handle insufficient balance.
Prioritize payments by due dates.
Schedule payments and process them at the scheduled time.
Search bills by provider and view transaction history.

**Build and run code**
- mvn build -> mvn install
- java -jar target/E-ServiceApp-0.0.1-jar-with-dependencies.jar

**List commands usage**
- CASH_IN 1000000
- LIST_BILL
- PAY 2 3
- ADD_BILL
- SEARCH_BILL_BY_PROVIDER VNPT
- SCHEDULE 2 2024-12-20
- LIST_SCHEDULED
- PROCESS_SCHEDULED
