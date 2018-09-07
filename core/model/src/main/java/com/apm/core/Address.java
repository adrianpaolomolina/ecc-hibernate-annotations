package com.apm.core;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

  private int streetNumber;
  private String streetName;
  private String barangay;
  private String city;
  private String zipCode;

  public Address() {
  }

  public Address ( int streetNumber, String streetName, String barangay,
              String city, String zipCode ) {
    this.streetNumber = streetNumber;
    this.streetName = streetName;
    this.barangay = barangay;
    this.city = city;
    this.zipCode = zipCode;
  }

  @Column( name = "Street_No" )
  public int getStreetNumber() {
    return this.streetNumber;
  }

  public void setStreetNumber ( int streetNumber ) {
    this.streetNumber = streetNumber;
  }

  @Column( name = "Street_Name" )
  public String getStreetName() {
    return this.streetName;
  }

  public void setStreetName ( String streetName ) {
    this.streetName = streetName;
  }

  @Column( name = "Barangay" )
  public String getBarangay() {
    return this.barangay;
  }

  public void setBarangay ( String barangay ) {
    this.barangay = barangay;
  }

  @Column( name = "City" )
  public String getCity() {
    return this.city;
  }

  public void setCity ( String city ) {
    this.city = city;
  }

  @Column( name = "Zip_Code")
  public String getZipCode() {
    return this.zipCode;
  }

  public void setZipCode ( String zipCode ) {
    this.zipCode = zipCode;
  }

  @Override
  public String toString(){
    return this.streetNumber + " " + this.streetName + " " + this.barangay + " " + this.city + " " + this.zipCode;
  }

}
