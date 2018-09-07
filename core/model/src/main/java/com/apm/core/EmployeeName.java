package com.apm.core;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeName {

  private String lastName;
  private String firstName;
  private String middleName;
  private String suffix;
  private String title;

  public EmployeeName(){
  }

  public EmployeeName ( String lastName, String firstName, String middleName, String suffix, String title ) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.suffix = suffix;
    this.title = title;
  }

  @Column( name = "Last_Name" )
  public String getLastName() {
    return this.lastName;
  }

  public void setLastName ( String lastName ) {
    this.lastName = lastName;
  }

  @Column( name = "First_Name" )
  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName ( String firstName ) {
    this.firstName = firstName;
  }

  @Column( name = "Middle_Name" )
  public String getMiddleName() {
    return this.middleName;
  }

  public void setMiddleName ( String middleName ) {
    this.middleName = middleName;
  }

  @Column( name = "Suffix" )
  public String getSuffix() {
    return this.suffix;
  }

  public void setSuffix ( String suffix ) {
    this.suffix = suffix;
  }

  @Column( name = "Title" )
  public String getTitle() {
    return this.title;
  }

  public void setTitle ( String title ) {
    this.title = title;
  }

  @Override
  public String toString(){
      return title + " " + lastName + ", " + firstName + " " + middleName + " " + suffix ;
  }

}
