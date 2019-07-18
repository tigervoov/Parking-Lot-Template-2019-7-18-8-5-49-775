CREATE table parking_lot(
    ID VARCHAR PRIMARY key,
    parking_name VARCHAR UNIQUE,
    capacity VARCHAR NOT NULL,
    location VARCHAR,
)
