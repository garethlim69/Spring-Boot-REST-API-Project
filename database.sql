create database db;
create table db.user (
userID varbinary(100) NOT NULL,
username varchar(255) NOT NULL,
displayusername varchar(255) NOT NULL,
password varchar(255) NOT NULL,
primary key(userID)
);
create table car (
car_id varchar(100) NOT NULL,
brand varchar(100) NOT NULL,
car_name varchar(100) NOT NULL,
description varchar(255) NOT NULL,
primary key(car_id)
);
create table variance (
var_id varchar(100) NOT NULL,
var_name varchar(100) NOT NULL,
price int NOT NULL,
car_car_id varchar(100) NOT NULL,
primary key(var_id)
);
insert into db.car values
("1", "Perodua",  "Myvi","bla bla bla"),
("2", "Toyota",  "Camry","bla bla bla"),
("3", "Toyota",  "Vios","bla bla bla"),
("4", "Honda",  "City", "bla bla bla"),
("5", "Honda",  "Civic","bla bla bla"),
("6", "Mazda",  "3 MPS","bla bla bla"),
("7", "Renault", "RS 250 Cup", "bla bla bla"),
("8", "Proton",  "Saga","bla bla bla"),
("9", "Proton",  "X-70","bla bla bla");
insert into db.variance (var_id, var_name, price, car_car_id) values
("1", "Full Spec", 175000, 1),
("2", "Manual Spec", 105000, 1),
("3", "Full Spec", 1375000, 2),
("4", "Manual Spec", 1105000, 2),
("5", "Full Spec", 175000, 3),
("6", "Manual Spec", 105000, 3),
("7", "Full Spec", 175000, 4),
("8", "Manual Spec", 105000, 4),
("9", "Full Spec", 175000, 5),
("10", "Manual Spec", 105000, 5),
("11", "Full Spec", 175000, 6),
("12", "Manual Spec", 105000, 6),
("13", "Full Spec", 175000, 7),
("14", "Manual Spec", 105000, 8),
("15", "Full Spec", 175000, 8),
("16", "Manual Spec", 105000, 9),
("17", "Full Spec", 175000, 9),
("18", "Manual Spec", 105000, 9),
("19", "Full Spec", 175000, 8),
("20", "Manual Spec", 105000, 5);