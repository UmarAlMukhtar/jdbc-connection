# Book Exchange Portal

A simple Java-based Book Exchange Portal with MySQL/MariaDB backend.  
This project allows users to **login** and view/manage books using a Java CLI (GUI planned for future) and connects to a database using environment variables.

---

## Features

- User login system (`login.java`).
- View books from the `books` table (`jdbc.java`).
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

### To view all books:

```powershell
javac -cp ".;lib\mysql-connector-j-9.4.0.jar;lib\dotenv-java-3.2.0.jar;src" src\jdbc.java
java -cp ".;lib\mysql-connector-j-9.4.0.jar;lib\dotenv-java-3.2.0.jar;src" jdbc
```

### To login as a user:

```powershell
javac -cp ".;lib\mysql-connector-j-9.4.0.jar;lib\dotenv-java-3.2.0.jar;src" src\login.java
java -cp ".;lib\mysql-connector-j-9.4.0.jar;lib\dotenv-java-3.2.0.jar;src" Login
```

> The program will prompt for username and password and verify them against the `users` table.

---


---

## Notes

- Keep your real `.env` file secret; do **not** commit it to GitHub.
- Currently, passwords are stored in **plain text**. Password hashing can be added later.
- The `db.sql` file contains sample data for testing users, books, and exchange requests.
