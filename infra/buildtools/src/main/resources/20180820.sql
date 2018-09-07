CREATE TABLE contacts (
    contact_id bigint NOT NULL,
    contact_type character varying(255),
    contact character varying(255),
    employee_id bigint REFERENCES employee(employee_id) NOT NULL,
    PRIMARY KEY ( contact_id, employee_id )
);

CREATE SEQUENCE contacts_contact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

  CREATE TABLE employee (
        employee_id bigint NOT NULL,
        last_name character varying(255),
        first_name character varying(255),
        middle_name character varying(255),
        suffix character varying(255),
        title character varying(255),
        birth_date date,
        hire_date date,
        gwa real,
        currently_hired boolean,
        street_no integer,
        street_name character varying(255),
        city character varying(255),
        barangay character varying(255),
        zip_code character varying(255),
        PRIMARY KEY ( employee_id )
    );

  CREATE SEQUENCE employee_employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

  CREATE TABLE employee_role (
      employee_id bigint REFERENCES employee(employee_id) NOT NULL,
      role_id bigint REFERENCES roles(role_id) NOT NULL,
      PRIMARY KEY ( employee_id, role_id )
  );

  CREATE TABLE roles (
    role_id bigint NOT NULL,
    roles character varying(255),
    PRIMARY KEY ( role_id )
);

CREATE SEQUENCE public.roles_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
