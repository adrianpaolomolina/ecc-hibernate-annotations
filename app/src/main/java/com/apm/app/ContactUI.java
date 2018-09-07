package com.apm.app;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import com.apm.core.Employee;
import com.apm.core.Contact;
import com.apm.core.EmployeeController;
import com.apm.utility.InputUtility;
import org.hibernate.Hibernate;

public class ContactUI {

  private static EmployeeController employeeController = new EmployeeController();

  private static void printContactUI() {
    System.out.println( "\n ======== CONTACT MENU ========\n\n "
                       + " 1. Create Contact \n "
                       + " 2. Update Contact \n "
                       + " 3. Remove Contact \n "
                       + " 4. Go Back To Main Menu \n " );
  }

  protected void runContactUI() {
    do {
      EmployeeUI.getInstance().displayEmployeeWithChild("CONTACTS");
      printContactUI();
      String selection = InputUtility.inputChoice("Select Operation: ");
      try {
        switch ( selection ) {
          case "1":
            System.out.println(employeeController.updateEmployeeElement(addNewContacts()));
            break;
          case "2":
            updateEmployeeContact();
            break;
          case "3":
            deleteEmployeeContact();
            break;
          case "4":
            break;
          default:
            System.out.println("Selection not valid! Input again! ");
            break;
        }
        if (selection.equals("4")) {
          break;
        }
      } catch ( Exception e ) {
      }
    }  while ( true );
  }

  protected static void updateEmployeeContact() throws Exception {
    Employee employee = null;
    Contact contact = null;
    int contactID = 0;
    int increment = 1;
    String newContact = "";
    EmployeeUI.getInstance().displayEmployeeWithChild("CONTACTS");
    int employeeId = InputUtility.inputPositiveNumber("\nInput ID of Employee to Update: ", false);
    try {
      employee = employeeController.getEmployeeWithContacts (employeeId);
      System.out.println("Employee: " + employee.getEmployeeName());
      for ( Contact cntcts : employee.getContacts() ) {
        System.out.println(increment + ") " + cntcts);
        increment++;
      }
      contactID = InputUtility.inputPositiveNumber("\nInput ID of Contact you want to update: ", false );
      List<Contact> contactsZ = new ArrayList<Contact>();
      contactsZ.addAll(employee.getContacts());
      contact = contactsZ.get(contactID-1);
      if ( contact.getContactType().equals("Landline") ) {
        newContact = InputUtility.inputLandline("\nEnter New Landline Number ( XXX-XXXX ): ");
      } else if ( contact.getContactType().equals("Mobile")) {
        newContact = InputUtility.inputMobile("\nEnter New Mobile Number ( XXXX - XXX - XXXX ): ");
      } else if ( contact.getContactType().equals("Email") ) {
        newContact = InputUtility.inputEmail( "\nEnter New Email: " );
      }
      System.out.println(employeeController.updateContact(employee, contact, newContact));
    } catch ( Exception e ) {
      if ( employee == null && contact == null ) {
        System.out.println(employeeController.message);
      } else {
        System.out.println(employeeController.message);
      }
    }
  }

  protected static void deleteEmployeeContact() throws Exception {
    Employee employee = null;
    Contact contact = null;
    int increment = 1;
    int contactID = 0;
    EmployeeUI.getInstance().displayEmployeeWithChild("CONTACTS");
    int employeeId = InputUtility.inputPositiveNumber("\n Input ID of Employee to Delete Contacts From: ", false);
    try {
      employee = employeeController.getEmployeeWithContacts(employeeId);
        for ( Contact cntcts : employee.getContacts() ) {
          System.out.println(increment + ") " + cntcts);
          increment++;
        }
      contactID = InputUtility.inputPositiveNumber("\nInput ID of Contact you want to update: ", false );
      List<Contact> contactsZ = new ArrayList<Contact>();
      contactsZ.addAll(employee.getContacts());
      contact = contactsZ.get(contactID-1);
      System.out.println(employeeController.deleteContact(employee, contact));
    } catch ( Exception e ) {
      if ( employee == null && contact == null ) {
        System.out.println(employeeController.message);
      } else {
        System.out.println(employeeController.message);
      }
    }
  }


  protected static Employee addNewContacts() throws Exception {
      System.out.println(EmployeeUI.retrieveEmployees());
      Employee employee = null;
      int employeeId = InputUtility.inputPositiveNumber("\n Input ID of Employee to Add Contacts To: ", false);
      employee = employeeController.getEmployeeWithContacts(employeeId);
      if ( employee == null ) {
        System.out.println("Employee does not Exist! ");
        throw new Exception();
      } else {
        return employeeController.addContact(employee, getInstance().getAllContacts(false));
      }
  }

  protected Set<Contact> getAllContacts ( boolean isNewContact ) {
    Set<Contact> contacts = new HashSet<>();
		while ( true ) {
			System.out.println("\n ======== Choose Contact Type ========\n\n " );
			System.out.println(" 1. LANDLINE \n ");
			System.out.println(" 2. MOBILE \n ");
			System.out.println(" 3. EMAIL \n ");
			System.out.println(" 4. DONE \n ");
			String option = InputUtility.inputChoice("What contact do you want to add?: ");
			switch ( option ) {
				case "1":
					contacts.add(employeeController.createNewContact( "Landline", InputUtility.inputLandline("Enter Landline: ")));
					break;
				case "2":
					contacts.add(employeeController.createNewContact("Mobile", InputUtility.inputMobile("Enter Mobile: ")));
					break;
				case "3":
					contacts.add(employeeController.createNewContact("Email", InputUtility.inputEmail("Enter Email: ")));
					break;
				case "4":
					if ( contacts.size() == 0 && isNewContact ) {
						System.out.println("\n YOU NEED TO ADD AT LEAST ONE CONTACT! \n");
						continue;
					}
					return contacts;
				default:
					System.out.println("Invalid Option!");
			}
		}
  }

  public static ContactUI getInstance() {
    return new ContactUI();
  }
}
