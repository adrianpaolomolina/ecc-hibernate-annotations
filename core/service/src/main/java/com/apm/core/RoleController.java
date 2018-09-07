package com.apm.core;

import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public class RoleController {

  private RolesDao rolesDao = new RolesDao();
  public static String message = "";

  public void addRole ( String role ) throws Exception {
    if ( role == null ) {
      message = "Role is null! ";
      throw new NullPointerException();
    } else {
      Roles newRole = new Roles(role);
      boolean exist = false;
      List<Roles> roles = retrieveAllRolesElements(Roles.class);
      for ( Roles currentRoles : roles ) {
        if ( currentRoles.getRole().equals(newRole.getRole()) ) {
          exist = true;
          break;
        }
      }
      if ( exist == false ) {
       rolesDao.saveRole(newRole);
      } else if ( exist == true ){
        message = " Role already exists! ";
        throw new Exception();
      }
    }
  }

  public String deleteRole ( int id ) throws Exception {
    try {
      Roles role = retrieveRole(id);
      return rolesDao.deleteRole(role);
    } catch ( Exception exception ) {
      throw exception;
    }
  }

  public Roles createNewRoles ( String roles ) {
    return new Roles(roles);
  }

  public Roles retrieveRole ( int roleID ) throws Exception {
    Roles role = null;
		try {
			role = rolesDao.getSpecificRole(Long.valueOf(roleID), Roles.class);
		} catch ( Exception e ) {
      message =  "Role does not exist! ";
      throw e;
		}
		return role;
  }

  public String editRole ( Roles role, String newRoleName ) throws Exception {
    String currentRoleName = "";
    Roles newRole = null;
    try {
      currentRoleName = role.getRole();
      role.setRole(newRoleName);
			newRole = rolesDao.getRole(role);
			message = "Role " + newRoleName + " already exists!" ;
    } catch ( Exception exception ) {
      if ( role == null ) {
        message = "Does it execute here?!";
      } else if ( newRoleName.equals(null) ) {
        message = "New Role Name is null";
      } else {
          rolesDao.updateRole(role);
          message = "Successfully updated role " + currentRoleName + " to " + role.getRole() + "!";
      }
		}
    return message ;
  }

  public List<Roles> retrieveAllRolesElements ( final Class<Roles> roles ) {
    return rolesDao.retrieveAllRoles(roles);
  }

}
