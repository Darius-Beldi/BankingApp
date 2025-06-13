# BankingApp

BankingApp is a simple Java console application for simulating basic banking functionalities, including user registration and login, card management, money transfers, and transaction history. It uses a MySQL database for persistent storage and logs actions for auditing purposes.

## Features

- **User Registration & Login:** Create an account or log in with your credentials.
- **Card Management:** Create and view cards associated with your account.
- **Money Transfers:** Transfer money between cards, including to contacts in your address book.
- **Transaction History:** View incoming and outgoing transactions for your cards.
- **Address Book:** Manage a list of contacts for easy transfers.
- **Audit Logging:** Every action is recorded in an audit log for traceability.
- **Database Initialization:** Option to initialize and populate the database with default data.

## Setup

### Prerequisites

- Java 8 or newer
- MySQL server running on `localhost:3306`
- Changing the `user` and `password` from ConnectionString class
- Import the database server module
  
### Database

On first run, for the app to prompt you to initialize and optionally populate the database set the flag `toInitializeDataBase` to true from `Menu` . The SQL schema and default data are managed by the app (`InitializeDatabase.java`). No manual SQL setup is required.

### Build & Run

1. Clone the repository.
2. Build the project using your preferred Java IDE or via command line:
    ```bash
    javac -d bin src/**/*.java
    ```
3. Run the application:
    ```bash
    java -cp bin Main
    ```

### Project Structure

```
src/
├── Connection/        # Database connection & SQL statements
├── Models/            # Core business models (User, Card, Transaction, etc.)
├── Services/          # Business logic & helper services
├── Main.java          # Entry point
└── AuditLog.CSV       # Audit log file
```

## Usage

- On startup, choose whether to initialize the database and/or populate it with default data.
- Register a new user or log in with an existing account.
- Use the console menu to navigate features: manage cards, transfer money, view transactions, and maintain your address book.
- All actions are logged to `src/AuditLog.CSV` for auditing.

## Customization

- **Database Connection:** Edit `src/Connection/ConnectionString.java` to change DB credentials.
- **Audit Log Path:** Edit `src/Services/AuditService.java` to change the audit log file location.

## Notes

- Passwords are stored as MD5 hashes.
- Error handling and input validation are basic and should be improved.
