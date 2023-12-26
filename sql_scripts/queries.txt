-- Note: Put in below queries in MySQL editor and execute step by step

-- 1. create database
create database j2ee;

-- 2. use the database created
use j2ee;

-- 3. below will create admin table insert the admin data for admin authentication, to verify data is insert or not execute select statement
create table admin
(
admin_id int primary key auto_increment,
admin_username varchar(200) not null unique,
admin_password varchar(100) not null
);
insert into admin(admin_username, admin_password) values("bmart1", "12345"), ("bmart2", "54321");
select * from admin;

-- 4. below will create state table, insert the states list used to link with customer table to reduce memory wastage, to verify data is insert or not execute select statement
create table state
(
	state_id int primary key auto_increment,
    state_name varchar(100)
);
insert into state(state_name) values ("Null"), ("Karnataka"), ("Tamil Nadu"), ("Andhra Pradesh"), ("Telangana"), ("Kerala");
insert into state(state_id, state_name) values (0, null);
select * from state;

-- 5. below will create gender table, insert the gender_types list used to link with customer table to reduce memory wastage, to verify data is insert or not execute select statement
create table gender
(
	gender_id int primary key auto_increment,
    gender_type varchar(10)
);
insert into gender(gender_type) values ("Null"), ("Male"), ("Female"), ("Other");
select * from gender;

-- 6. below query will create customer table for our crud operations.
create table customer_original
(
customer_id int primary key auto_increment,
customer_name varchar(200) not null,
customer_mobile bigint not null unique,
customer_purchased_state_id int default 0,
customer_email varchar(100) unique,
gender_id int default 0,
foreign key (customer_purchased_state_id) references state(state_id) on update cascade on delete cascade,
foreign key (gender_id) references gender(gender_id) on update cascade on delete cascade
);

-- NOTE: (Important codes for CRUD operations)
-- scratch code to insert data into customer table [using sub query].
insert into customer_original(customer_name, customer_mobile, customer_purchased_state_id, customer_email, gender_id) values ("Ram", 9876543210, (select state_id from state where state_name = "Karnataka"), "ram@bharat.in", (select gender_id from gender where gender_type = "Male"));

-- get all customer details
select * from customer_original;

-- below query to get customer by id (one customer at a time using join)
select c.customer_id as id, c.customer_name as name, c.customer_mobile as mobile, s.state_name as state, c.customer_email as email, g.gender_type as gender from customer_original c join  gender g on g.gender_id = c.gender_id join state s on s.state_id = c.customer_purchased_state_id where c.customer_id = 1;

-- below query will get all customer table rows with id, name, mobile, state, email, gender columns in ascending order based on id cloumn using join).
select c.customer_id as id, c.customer_name as name, c.customer_mobile as mobile, s.state_name as state, c.customer_email as email, g.gender_type as gender from customer_original c join  gender g on g.gender_id = c.gender_id join state s on s.state_id = c.customer_purchased_state_id order by c.customer_id;

-- below code to update customer based on id
update customer_original set customer_name = 'Navya', customer_mobile = 9876543100, customer_purchased_state_id = 1, customer_email = 'nay@bharat.in', gender_id = 2 where customer_id = 2;

-- below code to delete customer based on id
delete from customer_original where customer_id = 1;



