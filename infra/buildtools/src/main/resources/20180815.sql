CREATE DATABASE employeedb OWNER amolina;

CREATE TABLE addresses(
	addr_id SERIAL PRIMARY KEY,
	str_no VARCHAR(20) NOT NULL,
	street VARCHAR(25) NOT NULL,
	brgy VARCHAR(30) NOT NULL,
	city VARCHAR(30) NOT NULL,
	zipcode VARCHAR(10) NOT NULL
);

CREATE TABLE contacts(
	contact_id SERIAL PRIMARY KEY,
	landline VARCHAR(20),
	mobile VARCHAR(20),
	email VARCHAR(45)
);

CREATE TABLE employees (
	emp_id SERIAL PRIMARY KEY,
	lastname VARCHAR(25) NOT NULL,
	firstname VARCHAR(55) NOT NULL,
	middlename VARCHAR(25) NOT NULL,
	suffix VARCHAR(25),
	title VARCHAR(25),
	addr_id INT REFERENCES addresses(addr_id),
	contact_id INT REFERENCES contacts(contact_id),
	birthdate DATE NOT NULL,
	gwa FLOAT NOT NULL,
	hiredate DATE NOT NULL,
	currentlyhired BOOL NOT NULL
);

CREATE TABLE roles(
	role_id SERIAL PRIMARY KEY,
	role VARCHAR(25) NOT NULL
);

CREATE TABLE employee_role(
	emp_id INT REFERENCES employees(emp_id),
	role_id INT REFERENCES roles(role_id),
	primary key(emp_id,role_id)
);


INSERT INTO roles(role) VALUES ('CEO');
INSERT INTO roles(role) VALUES ('Manager');
INSERT INTO roles(role) VALUES ('Supervisor');
INSERT INTO roles(role) VALUES ('Back-end Developer');
INSERT INTO roles(role) VALUES ('Front-end Developer');
INSERT INTO roles(role) VALUES ('Designer');

INSERT INTO addresses(str_no, street, brgy, city, zipcode) 
	VALUES (343,'Apitong','Comembo', 'Makati City','1207');

INSERT INTO contacts(landline, mobile, email)
	VALUES('881-7612','09379487937','adrianpaolomolina@ymail.com');

INSERT INTO employees(lastname, firstname, middlename, suffix, title, addr_id, contact_id, birthdate, gwa, hiredate, currentlyhired)
	VALUES('Molina','Adrian Paolo','Mejillano','','Mr.',1, 1,'1995-11-17', 1.5,'2018-06-11','t');

INSERT INTO employee_role(emp_id, role_id) VALUES(1,3);

INSERT INTO addresses(str_no, street, brgy, city, zipcode) 
	VALUES (112,'Tolentino','Rizal', 'Makati','1208');

INSERT INTO contacts(landline, mobile, email)
	VALUES('','09278324356','denjilyn.bontia@yahoo.com');

INSERT INTO employees(lastname, firstname, middlename, suffix, title, addr_id, contact_id, birthdate, gwa, hiredate, currentlyhired)
	VALUES('Bontia','Denjilyn','Canares','','Ms.',2, 2,'1996-05-21',1.5,'2018-06-04','f');

INSERT INTO employee_role(emp_id, role_id) VALUES(2,5);



