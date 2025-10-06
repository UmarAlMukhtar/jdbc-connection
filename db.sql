-- Create database
CREATE DATABASE IF NOT EXISTS book_exchange_portal;
USE book_exchange_portal;
-- Users table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(100),
    join_date DATETIME DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO users (username, email, password_hash, full_name)
VALUES (
        'john_doe',
        'john@example.com',
        'hashed_password_1',
        'John Doe'
    );
-- Books table
CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100),
    isbn VARCHAR(20),
    description TEXT,
    owner_id INT NOT NULL,
    status ENUM('available', 'requested', 'exchanged') DEFAULT 'available',
    FOREIGN KEY (owner_id) REFERENCES users(user_id)
);
INSERT INTO books (title, author, isbn, description, owner_id)
VALUES (
        'The Great Gatsby',
        'F. Scott Fitzgerald',
        '9780743273565',
        'Classic novel set in the 1920s.',
        1
    );
-- Exchange Requests table
CREATE TABLE exchange_requests (
    request_id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    requester_id INT NOT NULL,
    request_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('pending', 'accepted', 'declined', 'completed') DEFAULT 'pending',
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (requester_id) REFERENCES users(user_id)
);
INSERT INTO exchange_requests (book_id, requester_id)
VALUES (1, 1);
-- Messages table (for communication between users)
CREATE TABLE messages (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    request_id INT,
    message_text TEXT NOT NULL,
    sent_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(user_id),
    FOREIGN KEY (receiver_id) REFERENCES users(user_id),
    FOREIGN KEY (request_id) REFERENCES exchange_requests(request_id)
);
INSERT INTO messages (sender_id, receiver_id, request_id, message_text)
VALUES (
        1,
        1,
        1,
        'Hello, I am interested in exchanging this book.'
    );