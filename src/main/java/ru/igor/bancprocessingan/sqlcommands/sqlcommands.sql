-- create table accounts (
--    id INT NOT NULL auto_increment,
--    name VARCHAR(20),
--    age INT,
--    balance INT,
--    PRIMARY KEY (id)
-- );
-- 
-- insert into accounts(name, age, balance) values('Igor', 30, 200000000);
-- select * from accounts;
-- 
-- 
-- 
-- show tables;
-- --drop table accounts;


create table bankaccounts (
   id INT NOT NULL auto_increment,
   name VARCHAR(20),
   age INT,
   balance INT,
   email VARCHAR(50) NOT NULL,
   PRIMARY KEY (id)
);


select * from bankaccounts;
pasha_schekotov@mail.ru
business_igor@mail.ru


create table bankacc (
   id INT NOT NULL auto_increment,
   name VARCHAR(20),
   age INT,
   balance INT,
   email VARCHAR(50) NOT NULL,
   role VARCHAR(20) NOT NULL,
   PRIMARY KEY (id)
);

select * from bankacc;

select * from bankacc;
update bankacc set balance = '2000' where name = 'Georg';

insert into bankacc (name, age, balance, email, role) values ('Vasya', '26', '1100', 'vasya@mail.ru', 'admin');