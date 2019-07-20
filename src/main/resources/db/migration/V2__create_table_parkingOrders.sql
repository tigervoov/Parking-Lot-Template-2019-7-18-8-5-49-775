CREATE table parking_orders(
    ID VARCHAR PRIMARY KEY,
    parking_name VARCHAR,
    car_number VARCHAR NOT NULL UNIQUE,
    start_time VARCHAR NOT NULL,
    end_time VARCHAR,
    status VARCHAR
)
