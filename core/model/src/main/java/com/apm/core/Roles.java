package com.apm.core;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Cacheable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table( name = "Roles" )
public class Roles {

  private Long roleID;
  private String role;

  public Roles() {
  }

  public Roles ( String role ) {
    this.role = role;
  }

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @Column( name = "Role_ID" )
  public Long getRoleID() {
    return this.roleID;
  }

  public void setRoleID ( Long roleID ) {
    this.roleID = roleID;
  }

  @Column( name = "Roles" )
  public String getRole() {
    return this.role;
  }

  public void setRole ( String role ) {
    this.role = role;
  }

  @Override
	public String toString() {
		return roleID + ": " + role;
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj == null &&!this.getClass().equals(obj.getClass()))
	    	return false;

	    Roles role2 = (Roles) obj;
	    return this.role.equals(role2.getRole());
   	}

   	@Override
   	public int hashCode() {
      	int tmp = 0;
      	tmp = (roleID + role).hashCode();
      	return tmp;
   	}
}
