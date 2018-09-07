package com.apm.app;

import java.util.logging.Level;
import com.apm.utility.InputUtility;

public class MainUI
{
    private static void printChooseFunction() {
      System.out.println ( " ======== MAIN MENU ======== \n\n "
                         + " 1. Employees \n "
                         + " 2. Roles \n "
                         + " 3. Exit \n "
                         );
    }

    private static void MainOperation() {
      do {
        System.out.print("\033\143\n");
        printChooseFunction();
        String selection = InputUtility.inputChoice("Select Operation: ");
        switch(selection){
          case "1":
            EmployeeUI.getInstance().runEmployeeUI();
            break;
          case "2":
            RoleUI.getInstance().runRoleUI();
            break;
          case "3":
            System.out.println("Thank you for using the program.");
            System.exit(0);
          default:
            System.out.println("Invalid Input!");
            break;
        }
      } while ( true );
    }

    public   static void main( String[] args )
    {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        MainOperation();
    }
}
