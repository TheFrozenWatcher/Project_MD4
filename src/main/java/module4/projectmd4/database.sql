CREATE DATABASE IF NOT EXISTS project_md4;
USE project_md4;



-- 1. User Table
CREATE TABLE users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(100) NOT NULL UNIQUE,
                       email VARCHAR(255) NOT NULL,
                       fullname VARCHAR(100) NOT NULL,
                       status BIT NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       avatar VARCHAR(255),
                       phone VARCHAR(15) NOT NULL UNIQUE,
                       address VARCHAR(255) NOT NULL,
                       created_at DATE,
                       updated_at DATE,
                       is_deleted BIT DEFAULT 0
);

-- 2. Role Table
CREATE TABLE roles (
                       role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       role_name ENUM('ADMIN', 'USER', 'MANAGER')
);

-- 3. Category Table
CREATE TABLE categories (
                            category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            category_name VARCHAR(100) NOT NULL,
                            description TEXT,
                            status BIT
);

-- 4. Product Table
CREATE TABLE products (
                          product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          sku VARCHAR(100) NOT NULL UNIQUE,
                          product_name VARCHAR(100) NOT NULL UNIQUE,
                          description TEXT,
                          unit_price DECIMAL(10,2),
                          stock_quantity INT,
                          image VARCHAR(255),
                          category_id BIGINT,
                          created_at DATE,
                          updated_at DATE,
                          FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

-- 5. Order Table
CREATE TABLE orders (
                        order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        serial_number VARCHAR(100) NOT NULL,
                        user_id BIGINT NOT NULL,
                        total_price DECIMAL(10,2),
                        status ENUM('WAITING', 'CONFIRM', 'DELIVERY', 'SUCCESS', 'CANCEL', 'DENIED'),
                        note VARCHAR(100),
                        receive_name VARCHAR(100),
                        receive_address VARCHAR(255),
                        receive_phone VARCHAR(15),
                        created_at DATE,
                        received_at DATE,
                        FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- 6. OrderDetail Table
CREATE TABLE order_details (
                               order_id BIGINT,
                               product_id BIGINT,
                               name VARCHAR(100),
                               unit_price DECIMAL(10,2),
                               order_quantity INT,
                               PRIMARY KEY (order_id, product_id),
                               FOREIGN KEY (order_id) REFERENCES orders(order_id),
                               FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- 7. ShoppingCart Table
CREATE TABLE shopping_cart (
                               shopping_cart_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               product_id BIGINT NOT NULL,
                               user_id BIGINT NOT NULL,
                               order_quantity INT,
                               FOREIGN KEY (product_id) REFERENCES products(product_id),
                               FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- 8. Address Table
CREATE TABLE address (
                         address_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         full_address VARCHAR(255) NOT NULL,
                         phone VARCHAR(15) NOT NULL,
                         receive_name VARCHAR(50) NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- 9. WishList Table
CREATE TABLE wish_list (
                           wish_list_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT NOT NULL,
                           product_id BIGINT NOT NULL,
                           FOREIGN KEY (user_id) REFERENCES users(user_id),
                           FOREIGN KEY (product_id) REFERENCES products(product_id)
);
