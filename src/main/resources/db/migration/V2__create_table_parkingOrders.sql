CREATE table parking_orders(
    ID VARCHAR PRIMARY KEY,
    parking_name VARCHAR UNIQUE,
    car_number VARCHAR NOT NULL,
    start_time TIME NOT NULL,
    end_time VARCHAR NOT NULL,
    status INT NOT NULL
)
