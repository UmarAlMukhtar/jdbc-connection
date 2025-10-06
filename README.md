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
- JAR files in `lib/`:
  - `mysql-connector-j-9.4.0.jar`
  - `dotenv-java-3.2.0.jar`

---

## Setup

1. Clone the repository:

```bash
git clone <your-repo-url>
cd BookExchangePortal
````

2. Create a `.env` file in the root with your database credentials:

```
DB_URL=jdbc:mysql://localhost:3307/book_exchange_portal
DB_USER=root
DB_PASS=yourpassword
```

3. Import the sample SQL included in this repository (`db.sql`) to create the database and tables:

```bash
mysql -u root -p < db.sql
```

---

## Compile and Run

```powershell
javac -cp ".;lib\mysql-connector-j-9.4.0.jar;lib\dotenv-java-3.2.0.jar;src" src\jdbc.java
java -cp ".;lib\mysql-connector-j-9.4.0.jar;lib\dotenv-java-3.2.0.jar;src" jdbc
```

> This will load the DB credentials from `.env` and print all books in the database.

---
