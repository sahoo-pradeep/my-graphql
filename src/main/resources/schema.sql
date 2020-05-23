CREATE TABLE orders (
    order_id BIGINT PRIMARY KEY,
    order_date TIMESTAMP,
    order_status VARCHAR(50),
    total_amount integer
);

CREATE TABLE transactions (
    transaction_id BIGINT PRIMARY KEY,
    order_id BIGINT,
    payment_option VARCHAR(50),
    amount integer,
    transaction_date TIMESTAMP,
    transaction_status VARCHAR(50)
);