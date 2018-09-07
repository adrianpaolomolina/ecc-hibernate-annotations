package com.apm.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import com.apm.utility.InputUtility;
import com.apm.core.Contact;
import com.apm.core.Roles;
import com.apm.core.Employee;
import com.apm.core.EmployeeController;

public class EmployeeUI {

  private static EmployeeController employeeController = new EmployeeController();
  public static String message = "";

  private static void printEmployeeMenu() {
    System.out.println( "\n ======== EMPLOYEE MENU ======== \n\n "
                      + " 1. Create Employee              7. Display Employee Details \n "
                      + " 2. Edit Employee Details        8. Display Employee Contacts \n "
                      + " 3. Edit Employee Contacts       9. Display Employee Roles \n "
                      + " 4. Add Role to Employee         10. Go Back To Main Menu \n "
                      + " 5. Delete Role From Employee \n "
                      + " 6. Delete Employee \n\n ");
  }

  public void runEmployeeUI() {
    do {
      printEmployeeMenu();
      String selection = InputUtility.inputChoice("Select Operation: ");
      try {
        switch ( selection ) {
          case "1":
            System.out.println ( addNewEmployee() );
            break;
          case "2":
            updateEmployeeDetails ( getEmployeeToEdit() );
            break;
          case "3":
            ContactUI.getInstance().runContactUI();
            break;
          case "4":
            addEmployeeRole ( getEmployeeToEdit() );
            break;
          case "5":
            deleteEmployeeRole ( getEmployeeToEdit() );
            break;
          case "6":
            System.out.println(deleteEmployee());
            break;
          case "7":
            displayEmployeeMenu();
            break;
          case "8":
            displayEmployeeWithChild("CONTACTS");
            break;
          case "9":
            displayEmployeeWithChild("ROLES");
            break;
          case "10":
            break;
            default: System.out.println("Invalid Option.");
          }
      } catch(Exception e) {
      }
      if (selection.equals("10")) {
      break;
    }
    } while (true);
  }

  private static void printDisplayEmployeeMenu() {
    System.out.println ( " \n\n======== Display Employee Menu ========\n\n "
                        + " 1. Sort by ID \n "
                        + " 2. Sort by GWA \n "
                        + " 3. Sort by Last Name \n "
                        + " 4. Sort by Hire Date \n "
                        + " 5. Go Back To Employee Menu \n ");
  }

  private static void displayEmployeeMenu() {
    do {
      printDisplayEmployeeMenu();
      List<Employee> employee = employeeController.retrieveAllEmployeeElements(Employee.class);
      String selection = InputUtility.inputChoice("Select Operation: ");
      switch ( selection ) {
        case "1":
        System.out.println(retrieveEmployees());
        break;
        case "2":
        employee = employeeController.sortByGwa(employee);
        break;
        case "3":
        employee = employeeController.sortByLastName(employee);
        break;
        case "4":
        employee = employeeController.sortByHireDate(employee);
        break;
        case "5":
        break;
        default:
      }
      if ( selection.equals("5") ) {
        break;
      } else if ( selection.equals("1")) {

      } else {
        employee.stream()
              .forEach(System.out::println);
      }
    } while ( true );
  }

  private static String addNewEmployee() throws Exception {
    System.out.print("\033\143\n");
    System.out.println( "\n ======== Add Employee ========\n\n " );
    String lastName = InputUtility.inputString( "Input Last Name: ", false );
    String firstName = InputUtility.inputString( "Input First Name: ", false);
    String middleName = InputUtility.inputString( "Input Middle Name: ", false);
    String suffix = InputUtility.inputString( "Input Suffix: ", true );
    String title = InputUtility.inputString( "Input Title: ", true );
    Date birthDate = InputUtility.inputDate( "Input Birth Date ( YYYY-MM-DD ): " );
    Date hireDate = InputUtility.inputDate( "Input Hire Date ( YYYY-MM-DD ): " );
    float gwa = InputUtility.inputFloat( "Input your GWA (Float) : ", false);
    boolean isCurrentlyHired = InputUtility.inputBoolean ( "Are You Currently Hired? ( Y or N ): ");
    System.out.println( "\n ======== ADDRESS ========\n\n ");
    int streetNumber = InputUtility.inputPositiveNumber( "Input Street Number: ", false );
    String barangay = InputUtility.inputString( "Input Barangay Name: ", false );
    String streetName = InputUtility.inputString( "Input Street Name: ", false );
    String city = InputUtility.inputString( "Input City: ", false );
    String zipCode = InputUtility.inputString( "Input Zip Code: ", false );
    Set<Contact> contacts = ContactUI.getInstance().getAllContacts ( true );
    Set<Roles> roles = RoleUI.getInstance().getAllRoles();
    employeeController.addEmployee(employeeController.createEmployeeName(lastName, firstName, middleName, suffix, title ), birthDate, hireDate,
                      gwa, isCurrentlyHired, employeeController.createNewAddress(streetNumber, streetName,
                      barangay, city, zipCode), contacts, roles );
    return "Employee Successfully Added! ";
    }

  private static String deleteEmployee() throws Exception {
    String message = "";
    System.out.print("\033\143\n");
    System.out.println( "\n ======== Delete Employee ========\n\n " );
    System.out.println( retrieveEmployees() );
    int id = InputUtility.inputPositiveNumber ( "Input ID of User To Delete: ", false);
    try {
      return employeeController.deleteEmployee ( id );
    } catch ( Exception e ) {
      System.out.println ( employeeController.message );
      throw e;
    }
  }

  protected static String retrieveEmployees(){
    System.out.print("\033\143\n");
  	StringBuilder stringBuilder = new StringBuilder();
  	List<Employee> employees = employeeController.retrieveAllEmployeeElements(Employee.class);
  	employees.stream()
         .sorted((employee1, employee2) -> Long.compare(employee1.getEmployeeId(), employee2.getEmployeeId()))
  			 .forEach(employee -> stringBuilder.append(employee + "\n" ));
  	return stringBuilder.toString();
  }

  protected static Employee getEmployeeToEdit() throws Exception {
    do {
      Employee employee = null;
      System.out.println(" \n ======== Choose Employee To Edit ");
      System.out.println(retrieveEmployees());
      int employeeId = InputUtility.inputPositiveNumber("Select Employee To Edit: ", false);
      try {
        employee = employeeController.getEmployeeWithRoles(employeeId);
        return employee;
      } catch ( Exception e ) {
        System.out.println("Employee does not exist!");
        throw e;
      }
    } while ( true );
  }

  private static void updateEmployeeDetails ( Employee employee ) throws Exception {
    do {
      System.out.print("\033\143\n");
      System.out.println(employee);
      printUpdateEmployeeDetailsMenu();
      String option = InputUtility.inputChoice ( " \nSelect what to edit: " );
      switch ( option ) {
        case "1":
        employee = editEmployeeName(employee);
        break;
        case "2":
        employee = editBirthDate(employee);
        break;
        case "3":
        employee = editAddress(employee);
        break;
        case "4":
        employee = editGwa(employee);
        break;
        case "5":
        employee = editJobInformation(employee);
        break;
        case "6":
        break;
        default:
        System.out.println( "Option not Valid! ");
        break;
      }
      if ( option.equals("6") ) {
        break;
      } else {
        System.out.println( "\n" + employeeController.updateEmployeeElement(employee));
        break;
      }
    } while ( true );
  }

  private static Employee editEmployeeName ( Employee employee ) {
    System.out.println (" \n ======== Edit Employee Name ======== \n\n ");
  	employee.getEmployeeName().setLastName(InputUtility.inputString("Input Last Name : ", false));
  	employee.getEmployeeName().setFirstName(InputUtility.inputString("Input First Name : ", false));
  	employee.getEmployeeName().setMiddleName(InputUtility.inputString("Input Middle Name : ", false));
  	employee.getEmployeeName().setSuffix(InputUtility.inputString("Input Suffix : ", true) );
  	employee.getEmployeeName().setTitle(InputUtility.inputString("Input Title : ", true) );
    return employee;
  }

  private static void printUpdateEmployeeDetailsMenu() {
  System.out.println( " \n ======== Edit Employee Details ========\n\n "
                      + " 1. Edit Name \n "
                      + " 2. Edit Birth Date \n "
                      + " 3. Edit Address \n "
                      + " 4. Edit GWA \n "
                      + " 5. Edit Job Information \n "
                      + " 6. Go Back To Edit Employee Menu ");
  }

  private static Employee editBirthDate ( Employee employee ) throws Exception {
    System.out.println(" \n\n ======== Edit Birth Date ======== \n\n ");
    System.out.println("Current Birth Date: " + employee.getBirthDate() + "\n\n ");
    Date birthDate = InputUtility.inputDate("Input Birthdate (yyyy-mm-dd): ");
    employee.setBirthDate(birthDate);
    return employee;
  }

  private static Employee editAddress ( Employee employee ) throws Exception {
    System.out.println(" \n\n ======== Edit Address ======== \n\n ");
    System.out.println( "Current Address: " + employee.getAddress().toString() + "\n\n ");
    employee.getAddress().setStreetNumber(InputUtility.inputPositiveNumber("Input Street No: ", false));
  	employee.getAddress().setStreetName(InputUtility.inputString("Input Street: ", false));
  	employee.getAddress().setBarangay(InputUtility.inputString("Input Barangay: ", false));
  	employee.getAddress().setCity(InputUtility.inputString("Input City: ", false));
  	employee.getAddress().setZipCode(InputUtility.inputString("Input Zipcode: ", false));
  	return employee;
  }

  private static Employee editGwa ( Employee employee ) throws Exception {
    System.out.println(" \n\n ======== Edit GWA ======== \n\n " );
    System.out.println("Current GWA: " + employee.getGwa() + "\n\n ");
    employee.setGwa(InputUtility.inputFloat( "Input GWA: ", false ));
    return employee;
  }

  private static Employee editJobInformation ( Employee employee ) throws Exception {
    System.out.println( " \n\n ======== Edit Job Information ======== \n\n " );
    System.out.println( " Currently Hired : " + employee.getIsCurrentlyHired()
                        + " \n Current Hire Date: " + employee.getHireDate() + "\n\n ");
    employee.setIsCurrentlyHired(InputUtility.inputBoolean( "Are You Currently Hired?: " ));
    employee.setHireDate(InputUtility.inputDate( "Input Hire Date: " ) );
    return employee;
  }

  protected static void addEmployeeRole( Employee employee ) throws Exception {
    System.out.println( " \n\n ======== Available Employee Roles ======== \n\n ");
    System.out.println( " Employee ID: " + employee.getEmployeeId()
                      + " Employee   : " + employee.getEmployeeName() + "\n");
    System.out.println( RoleUI.getInstance().getEmployeeRolesToAdd(employee));
    int id = InputUtility.inputPositiveNumber( " Input Role ID: ", false );
    try {
      employeeController.getNewEmployeeRoles(employee, id);
      employeeController.updateEmployeeElement( employee );
      System.out.println("Successfully Added Role to " + employee.getEmployeeName());
    } catch ( Exception e ) {
      System.out.println("\n That Role is not available! ");
      throw e;
    }
  }

  protected static void deleteEmployeeRole ( Employee employee ) throws Exception {
    System.out.println(" \n\n ======== Available Employee Roles ======== \n\n ");
    System.out.println(RoleUI.getInstance().getEmployeeRolesToDelete(employee));
    int id = InputUtility.inputPositiveNumber(" Input Role ID: ", false);
    try {
      employeeController.removeEmployeeRole(employee, id);
      System.out.println("Successfully Removed role from " + employee.getEmployeeName());
      employeeController.updateEmployeeElement ( employee );
    } catch ( NullPointerException e ) {
      System.out.println( "\n Employee does not have that role! " );
      throw e;
    }
    employeeController.updateEmployeeElement ( employee );
  }

  protected static void displayEmployeeWithChild ( String type ) {
      System.out.println("=======================");
      List<Object[]> employees = null;
      try {
        if ( type.equals("CONTACTS")) {
          employees = (List<Object[]>) employeeController.getEmployeesAndContacts();
        } else if ( type.equals("ROLES")) {
          employees = (List<Object[]>) employeeController.getEmployeesAndRoles();
        }
        Long previousID = new Long(0);
        for ( Object[] employee : employees ) {
          if ( previousID.equals(employee[0]) ){
            System.out.println("          " + employee[4] + " : " + employee[5]);
          } else {
            System.out.println("\nEmployee ID: " + employee[0]);
            System.out.println("Employee: " + employee[1] + ", " + employee[2] + " " + employee[3]);
            System.out.println("\n          " + employee[4] + " : " + employee[5]);
          }
          previousID = (Long) employee[0];
        }
      } catch ( Exception e ) {
        e.printStackTrace();
      }
  }

  public static EmployeeUI getInstance() {
    return new EmployeeUI();
  }

}
