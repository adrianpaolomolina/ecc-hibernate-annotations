package com.apm.core;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.Cacheable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table( name = "Employee" )
public class Employee {

  private Long employeeId;
  private EmployeeName employeeName;
  private Date birthDate;
  private Date hireDate;
  private float gwa;
  private boolean isCurrentlyHired;
  private Address address;
  private Set<Contact> contacts;
  private Set<Roles> roles;

  public Employee () {
  }

  public Employee ( EmployeeName employeeName, Date birthDate, Date hireDate, float gwa, boolean isCurrentlyHired, Address address,
       Set<Contact> contacts, Set<Roles> roles ) {
        this.employeeName = employeeName;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.gwa = gwa;
        this.isCurrentlyHired = isCurrentlyHired;
        this.address = address;
        this.contacts = contacts;
        this.roles = roles;
  }

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @Column(name = "Employee_ID", unique = true, nullable = false )
  public Long getEmployeeId() {
    return this.employeeId;
  }

  public void setEmployeeId ( Long employeeId ) {
    this.employeeId = employeeId;
  }

  @Embedded
  public EmployeeName getEmployeeName() {
    return this.employeeName;
  }

  public void setEmployeeName ( EmployeeName employeeName ) {
    this.employeeName = employeeName;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "Birth_Date")
  public Date getBirthDate() {
    return this.birthDate;
  }

  public void setBirthDate ( Date birthDate ) {
    this.birthDate = birthDate;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "Hire_Date")
  public Date getHireDate() {
    return this.hireDate;
  }

  @Temporal(TemporalType.DATE)
  public void setHireDate ( Date hireDate ) {
    this.hireDate = hireDate;
  }

  @Column( name = "GWA")
  public float getGwa() {
    return this.gwa;
  }

  public void setGwa ( float gwa ) {
    this.gwa = gwa;
  }

  @Column( name = "Currently_Hired" )
  public boolean getIsCurrentlyHired() {
    return this.isCurrentlyHired;
  }

  public void setIsCurrentlyHired ( boolean isCurrentlyHired ) {
    this.isCurrentlyHired = isCurrentlyHired;
  }

  @ElementCollection
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @CollectionTable(name="Contacts", joinColumns=@JoinColumn(name="Employee_ID"))
  public Set<Contact> getContacts() {
    return this.contacts;
  }

  public void setContacts ( Set<Contact> contacts ) {
    this.contacts = contacts;
  }

  @ManyToMany( fetch = FetchType.LAZY )
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JoinTable(name="Employee_Role",
  joinColumns=@JoinColumn(name="Employee_ID"),
  inverseJoinColumns=@JoinColumn(name="Role_ID"))
  public Set<Roles> getRoles() {
    return this.roles;
  }

  public void setRoles ( Set<Roles> roles ) {
    this.roles = roles;
  }

  @Embedded
  public Address getAddress() {
    return this.address;
  }

  public void setAddress ( Address address ) {
    this.address = address;
  }

  @Override
  public String toString(){
      return "  Employee ID     : " + employeeId + "\n "
                        + " Employee Name   :" + employeeName + "\n "
                        + " Birth Date      : " + birthDate + "\n "
                        + " Hire Date       : " + hireDate + "\n "
                        + " GWA             : " + gwa + "\n "
                        + " Currently Hired : " + isCurrentlyHired + "\n "
                        + " Address         : " + address + "\n\n ";
  }

}
