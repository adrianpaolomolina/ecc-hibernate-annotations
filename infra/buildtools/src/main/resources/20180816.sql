CREATE TABLE employees(
	employeeId INTEGER NOT NULL,
	lastName VARCHAR(25),
	firstName VARCHAR(55),
	middleName VARCHAR(25),
	suffix VARCHAR(25),
	title VARCHAR(25),
	streetNumber INTEGER,
	street VARCHAR(25),
	barangay VARCHAR(30),
	city VARCHAR(30),
	zipCode VARCHAR(10),
	birthDate DATE,
	gwa FLOAT,
	hireDate DATE,
	isCurrentlyHired BOOLEAN
);

CREATE TABLE contacts(
	contactId SERIAL PRIMARY KEY,
	employeeId SERIAL REFERENCES employees(employeeId),
	contactType VARCHAR(20),
	contact VARCHAR(30)
);

CREATE TABLE roles(
	roleID SERIAL PRIMARY KEY,
	role VARCHAR(25) NOT NULL
);

CREATE TABLE employee_role(
	employeeId INT REFERENCES employees(employeeId),
	roleID INT REFERENCES roles(roleID),
	PRIMARY KEY(employeeId,roleID)
);

INSERT INTO roles(role) VALUES ('CEO');
INSERT INTO roles(role) VALUES ('Manager');
INSERT INTO roles(role) VALUES ('Supervisor');
INSERT INTO roles(role) VALUES ('Back-End Developer');
INSERT INTO roles(role) VALUES ('Front-End Developer');
INSERT INTO roles(role) VALUES ('Designer');
INSERT INTO roles(role) VALUES ('QA Tester');

INSERT INTO employees(lastName, firstName, middleName, suffix, title, streetNumber, street, barangay, city, zipCode, birthDate, gwa, hireDate, isCurrentlyHired)
	VALUES('Molina','Adrian Paolo','Mejillano','','Mr.', 343,'Apitong','Comembo', 'Makati','1207','1995-11-17',2,'2018-06-11','t');

INSERT INTO contacts(employeeId,contactType,contact) VALUES(1,'Landline','881-7612');
INSERT INTO contacts(employeeId,contactType,contact) VALUES(1,'Mobile','0937-948-7937');
INSERT INTO contacts(employeeId,contactType,contact) VALUES(1,'Email','adrianpaolomolina@ymail.com');
INSERT INTO contacts(employeeId,contactType,contact) VALUES(1,'Email','adrianpaolomolina@gmail.com');

INSERT INTO employee_role(employeeId, roleID) VALUES(1,3);
INSERT INTO employee_role(employeeId, roleID) VALUES(1,4);
INSERT INTO employee_role(employeeId, roleID) VALUES(1,8);

INSERT INTO employees(lastname, firstname, middlename, suffix, title, str_no, street, brgy, city, zipcode, birthdate, gwa, hiredate, currentlyhired)
	VALUES('Bontia','Denjilyn','Canares','','Ms.',112,'Tolentino','Rizal', 'Makati','1208','1996-05-21',1.75,'2018-06-04','t');

INSERT INTO contacts(employeeId,contactType,contact) VALUES(2,'Mobile','0927-832-4356');
INSERT INTO contacts(employeeId,contactType,contact) VALUES(2,'Email','denjilyn.bontia@yahoo.com');

INSERT INTO employee_role(employeeId, roleID) VALUES(2,4);
INSERT INTO employee_role(employeeId, roleID) VALUES(2,5);
