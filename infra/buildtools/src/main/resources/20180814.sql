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



