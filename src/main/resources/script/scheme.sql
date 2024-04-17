CREATE DATABASE expense_tracking_db;

CREATE TABLE users_tb (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    profile_image VARCHAR(255)
);

CREATE TABLE categories_tb (
    category_id SERIAL PRIMARY KEY,
    name VARCHAR (100),
    description VARCHAR (250),
    user_id INT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users_tb (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE expenses_tb (
    expense_id SERIAL PRIMARY KEY,
    amount INT,
    description VARCHAR (250),
    date TIMESTAMP,
    user_id INT UNIQUE,
    category_id INT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users_tb (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories_tb (category_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE otp_tb(
            opt_id SERIAL PRIMARY KEY,
            otp_code VARCHAR(6) NOT NULL,
            issued_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
            expiration_time TIMESTAMP NOT NULL,
            verified BOOLEAN NOT NULL DEFAULT FALSE,
            user_id INT UNIQUE,
            FOREIGN KEY (user_id) REFERENCES users_tb (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);