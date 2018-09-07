package com.apm.core;

import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.hibernate.Hibernate;

public class EmployeeController {

  private EmployeeDao employeeDao = new EmployeeDao();
  private RolesDao rolesDao = new RolesDao();
  public static String message = "";

  public void addEmployee ( EmployeeName employeeName, Date birthDate, Date hireDate, float gwa, boolean isCurrentlyHired,
                      Address address, Set<Contact> contacts, Set<Roles> roles ) {
      Employee employee = new Employee(employeeName, birthDate, hireDate,
                      gwa, isCurrentlyHired, address, contacts, roles);
      employeeDao.saveEmployee(employee);
  }

 public String deleteEmployee ( int id ) throws Exception {
    try {
      Employee employee = getEmployee(id);
      return employeeDao.deleteEmployee(employee);
    } catch ( NullPointerException e ) {
      message = " Employee does not exist! ";
      throw e;
    }
  }

  public Employee getEmployee ( int id ) throws Exception {
    Employee employee = new Employee();
    employee = employeeDao.getSpecificEmployee(Long.valueOf(id), Employee.class);
    System.out.println(employee.getEmployeeName());
    return employee;
  }

  public Employee getEmployeeWithContacts ( int id ) throws Exception {
    try {
      Employee employee = new Employee();
      employee = employeeDao.getSpecificWithChild(Long.valueOf(id), Employee.class, "CONTACTS");
      return employee;
    } catch ( Exception e ) {
      message = "Employee does not exist!";
      throw e;
    }
  }

  public Employee getEmployeeWithRoles ( int id ) throws Exception {
    Employee employee = new Employee();
    employee = employeeDao.getSpecificWithChild(Long.valueOf(id), Employee.class, "ROLES");
    return employee;
  }

  public EmployeeName createEmployeeName ( String lastName, String firstName, String middleName, String suffix, String title ) {
    return new EmployeeName(lastName, firstName, middleName, suffix, title);
  }

  public Address createNewAddress ( int streetNumber, String streetName, String barangay,
                        String city, String zipCode ) {
    return new Address(streetNumber, streetName, barangay, city, zipCode);
  }

  public Contact createNewContact ( String contactType, String contact ) {
    return new Contact(contactType, contact);
  }

  public List<Employee> retrieveAllEmployeeElements ( final Class<Employee> employee ) {
      return employeeDao.retrieveAllEmployees(employee);
  }

  public List<Object[]> getEmployeesAndContacts(){
      List<Object[]> employees = (List<Object[]>) employeeDao.getAllEmployeesWithChild( Employee.class, "CONTACTS" );
      return employees;
  }

  public List<Object[]> getEmployeesAndRoles(){
    List<Object[]> employees = (List<Object[]>) employeeDao.getAllEmployeesWithChild( Employee.class, "ROLES" );
    return employees;
  }

  public Employee getNewEmployeeRoles ( Employee employee, int roleID ) throws Exception {
		Set<Roles> roles = employee.getRoles();
		Roles role = rolesDao.getSpecificRole(Long.valueOf(roleID), Roles.class);
    if ( role == null ) {
      throw new NullPointerException();
    } else {
      roles.add(role);
  		employee.setRoles(roles);
  		return employee;
    }
  }

  public Employee removeEmployeeRole ( Employee employee, int roleID ) throws Exception {
    Set<Roles> roles = employee.getRoles();
		Roles role = rolesDao.getSpecificRole(Long.valueOf(roleID), Roles.class);
    if ( role == null ) {
      throw new NullPointerException();
    } else {
      roles.remove(role);
  		employee.setRoles(roles);
  		return employee;
    }
  }

  public Employee addContact ( Employee employee, Set<Contact> contacts ) {
		contacts.forEach(contact -> {
			employee.getContacts().add(contact);
		});
		return employee;
	}

  public String deleteContact ( Employee employee, Contact contact ) throws Exception {
    if ( employee.getContacts().size() == 1 ) {
      message =  "Cannot Delete! The Contact of an Employee should at least be 1! ";
      throw new Exception();
    } else if ( !employee.getContacts().contains(contact) ) {
      message = employee.getEmployeeName() + " does not have that Contact!";
      throw new Exception();
    }
      employee.getContacts().remove(contact);
      return updateEmployeeElement(employee);
  }


  public String updateEmployeeElement ( Employee employee ) {
    try {
      employeeDao.updateEmployee (employee);
      return "Database successfully Updated!";
    } catch ( Exception e ) {
      return "Failed to update database! Please check the details.";
      }
    }

  public String updateContact ( Employee employee, Contact contact, String newContact ) throws Exception {
      if ( !employee.getContacts().contains(contact) ) {
        message = employee.getEmployeeName() + " does not have that Contact";
        throw new Exception();
      }
    try {
      employee.getContacts().remove(contact);
      contact.setContact(newContact);
      employee.getContacts().add(contact);
      employeeDao.updateEmployee(employee);
      return "Successfully changed contact details to " + newContact;
    } catch ( Exception e ) {
      message = " Failed to change contact details! ";
      e.printStackTrace();
      throw e;
    }
  }

  public List<Employee> sortByGwa(List<Employee> employee){
    Collections.sort(employee, new Comparator<Employee>() {
      public int compare ( Employee employee1, Employee employee2 ) {
        return Float.valueOf(employee1.getGwa()).compareTo(Float.valueOf(employee2.getGwa()));
      }
    });
    return employee;
  }

  public List<Employee> sortByLastName ( List<Employee> employee ) {
    employee = employeeDao.getEmployeeSortByLastName(Employee.class);
    return employee;
  }

  public List<Employee> sortByHireDate ( List<Employee> employee ) {
    employee = employeeDao.getEmployeeSortByHireDate(Employee.class);
    return employee;
  }

}
