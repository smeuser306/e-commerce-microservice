-- Drop table if exists
DROP TABLE IF EXISTS orders;

-- Create orders table
CREATE TABLE orders (
    id VARCHAR(36) PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email_address VARCHAR(100) NOT NULL,
    address_line VARCHAR(255) NOT NULL,
    country VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    card_name VARCHAR(100) NOT NULL,
    card_number VARCHAR(100) NOT NULL,
    card_expiry VARCHAR(10) NOT NULL,
    card_cvv VARCHAR(5) NOT NULL,
    payment_method INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);