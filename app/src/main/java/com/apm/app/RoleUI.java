package com.apm.app;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import com.apm.core.Roles;
import com.apm.utility.InputUtility;
import com.apm.core.Employee;
import com.apm.core.RoleController;

import org.hibernate.exception.ConstraintViolationException;

public class RoleUI {

  private static RoleController roleController = new RoleController();

  public static String message = "";

  private static void printRoleMenu() {
    System.out.println ( "\n ======== ROLE MENU ========\n\n "
                       + " 1. Create Role \n "
                       + " 2. Update Role \n "
                       + " 3. List Roles \n "
                       + " 4. Delete Roles \n "
                       + " 5. Go Back To Main Menu \n ");
  }

  public void runRoleUI() {
    do {
      printRoleMenu();
      String selection = InputUtility.inputChoice( "Select Operation: " );
      try {
        switch( selection ) {
          case "1":
            System.out.println(addNewRole());
            break;
          case "2":
            message = updateRole();
            break;
          case "3":
            System.out.println(retrieveAllRoles());
            break;
          case "4":
            System.out.println(removeRole());
            break;
          case "5":
            break;
          default:
            System.out.println("\nInput not valid!");
        }
        if ( selection.equals("5") ) {
          break;
        }
      } catch ( Exception e ) {
      }
    } while ( true );
  }

  private static String updateRole() throws Exception {
      System.out.println(retrieveAllRoles());
      Roles role = null;
      role = roleController.retrieveRole(InputUtility.inputPositiveNumber("\n Input ID of Role to Update: ", false));
      if ( role == null ) {
        System.out.println("Role does not exist!");
        throw new Exception();
      } else {
        message = roleController.editRole(role, InputUtility.inputString("\n Input New Role Name: ", false));
        System.out.println(message);
        return message;
      }
  }

  private static String addNewRole () throws Exception {
    System.out.println(retrieveAllRoles());
    try {
      roleController.addRole(InputUtility.inputString("\n Input New Role To Add: ", false));
      return "Successfully Added New Role!";
    } catch ( Exception e ) {
      System.out.println(roleController.message);
      throw e;
    }
  }

  private static String removeRole () throws Exception {
      System.out.print("\033\143\n");
      System.out.println("\n ======== Delete Role ========\n\n " );
      System.out.println(retrieveAllRoles());
      int id = InputUtility.inputPositiveNumber("Input ID of Role To Delete: ", false);
      try {
          return roleController.deleteRole(id);
      } catch ( ConstraintViolationException e ) {
        System.out.println("Role is still assigned to at least one employee!");
        throw e;
      } catch ( Exception e ) {
        System.out.println("Role does not exist!");
        throw e;
      }
  }

  protected Set<Roles> getAllRoles() {
    Set<Roles> roles = new HashSet<>();
    List<Roles> availableRoles = roleController.retrieveAllRolesElements(Roles.class);
		while ( true )	{
			System.out.println( " \n\n ======== List of Roles ======== " );
			availableRoles = availableRoles.stream()
		 						   .filter(role -> !roles.contains(role))
		  			 			 .sorted((role1,role2) -> Long.compare(role1.getRoleID(), role2.getRoleID()))
			   		  		 .collect(Collectors.toList());
			if ( availableRoles.size()==0 ) {
				break;
			}
			availableRoles.forEach(System.out::println);
			int roleID = InputUtility.inputPositiveNumber("Input Role To Add (Enter role number): ", false);
			try {
				roles.add(roleController.retrieveRole(roleID));
			} catch ( Exception exception ) {
				System.out.println("Role not found! ");
				continue;
			}
			boolean isDone = InputUtility.inputBoolean("Are you done adding roles (Y|N): ");
			if ( isDone && roles.size()!=0 ) {
				break;
			} else if ( isDone && roles.size()==0 ) {
				System.out.println("Please add atleast one role!");
				continue;
			}
		}
    return roles;
  }

  public static RoleUI getInstance() {
    return new RoleUI();
  }

  private static String retrieveAllRoles() throws Exception {
    System.out.print("\033\143\n");
    StringBuilder stringBuilder = new StringBuilder();
    List<Roles> roles = roleController.retrieveAllRolesElements(Roles.class);
    roles.stream()
      .sorted((role1, role2) -> Long.compare(role1.getRoleID(), role2.getRoleID()))
      .forEach(role -> stringBuilder.append(role + "\n"));
    return stringBuilder.toString();
  }

  protected String getEmployeeRolesToAdd ( Employee employee ) {
		StringBuilder stringBuilder = new StringBuilder();
		roleController.retrieveAllRolesElements(Roles.class).stream()
											 .filter(role -> !employee.getRoles().contains(role))
											 .sorted((role1, role2) -> Long.compare(role1.getRoleID(), role2.getRoleID()))
											 .forEach(role -> stringBuilder.append(role + "\n"));
		return stringBuilder.toString();
	}

  protected String getEmployeeRolesToDelete ( Employee employee ) {
		StringBuilder stringBuilder = new StringBuilder();
		roleController.retrieveAllRolesElements(Roles.class).stream()
											 .filter(role -> employee.getRoles().contains(role))
											 .sorted((role1,role2) -> Long.compare(role1.getRoleID(), role2.getRoleID()))
											 .forEach(role -> stringBuilder.append(role + "\n"));
		return stringBuilder.toString();
	}

}
