DROP TABLE IF EXISTS stock;

create table stock 
(
com_code VARCHAR(255) NOT NULL,
currency_code CHAR(3) NOT NULL,
price decimal NOT NULL,
time_stamp timestamp NOT NULL default current_timestamp(),
PRIMARY KEY (price, time_stamp) 
);