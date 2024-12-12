CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       nickname VARCHAR(50) NOT NULL
);

CREATE TABLE settlements (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(100) NOT NULL,
                             owner_id BIGINT NOT NULL
);

CREATE TABLE expenses (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          amount DECIMAL(15, 2) NOT NULL,
                          date DATE NOT NULL,
                          settlement_id BIGINT NOT NULL,
                          user_id BIGINT NOT NULL
);
