# Book Exchange Portal

A simple Java-based Book Exchange Portal with MySQL/MariaDB backend.  
This project allows users to view and manage books using a Java CLI (or GUI in the future) and connects securely to a database using environment variables.

---

## Features

- View books from the `books` table.
- Connects to MariaDB/MySQL using JDBC.
- Uses environment variables for database credentials (`.env`).

---

## Requirements

- Java JDK 17+
- MariaDB/MySQL server running
- Maven (optional, if using dependencies like `java-dotenv`) or the `.jar` files in `lib/`:
  - `mysql-connector-j-9.4.0.jar`
  - `java-dotenv-5.2.2.jar` (if using `.env`)

---

## Setup

1. Clone the repository:

```bash
git clone <your-repo-url>
cd BookExchangePortal
```

2. Create a `.env` file in the root:

```
DB_URL=jdbc:mysql://localhost:3307/book_exchange_portal
DB_USER=root
DB_PASS=yourpassword
```

3. Import the sample SQL included in this repository (`db.sql`) to create the database and tables:

```bash
# Using MariaDB/MySQL CLI
mysql -u root -p < db.sql
```

> This will create the `book_exchange_portal` database and the `books` table with sample data.

---

## Run

Compile and run the project:

```bash
javac -cp ".;lib\mysql-connector-j-9.4.0.jar;lib\java-dotenv-5.2.2.jar;src" src\jdbc.java
java -cp ".;lib\mysql-connector-j-9.4.0.jar;lib\java-dotenv-5.2.2.jar;src" jdbc
```

> This will load the DB credentials from `.env` and print all books in the database.

---
