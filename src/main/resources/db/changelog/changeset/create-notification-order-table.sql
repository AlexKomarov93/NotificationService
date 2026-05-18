CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    sale NUMERIC(10,2) NOT NULL,
    total_price NUMERIC(10,2) NOT NULL,
    user_id BIGINT NOT NULL
);
