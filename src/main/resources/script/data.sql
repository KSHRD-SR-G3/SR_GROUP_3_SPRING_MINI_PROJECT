CREATE TABLE users_tb (
                          user_id SERIAL PRIMARY KEY,
                          email VARCHAR (255),
                          password VARCHAR (255),
                          profile_image VARCHAR (255)
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
                             user_id INT UNIQUE ,
                             category_id INT UNIQUE,
                             FOREIGN KEY (user_id) REFERENCES users_tb (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
                             FOREIGN KEY (category_id) REFERENCES categories_tb (category_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE otps_tb (
                         opt_id SERIAL PRIMARY KEY,
                         opt_code VARCHAR (100),
                         issued_at TIMESTAMP,
                         expiration TIMESTAMP,
                         verify BOOLEAN,
                         user_id INT UNIQUE,
                         FOREIGN KEY (user_id) REFERENCES users_tb (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);