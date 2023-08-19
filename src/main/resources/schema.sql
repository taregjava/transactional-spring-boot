-- src/main/resources/schema.sql

CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    ip_address VARCHAR(255)
);
