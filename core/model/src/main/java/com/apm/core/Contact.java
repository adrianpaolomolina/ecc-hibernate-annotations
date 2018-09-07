package com.apm.core;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Embeddable;

@Embeddable
public class Contact {

  private String contactType;
  private String contact;

  public Contact(){
  }

  public Contact ( String contactType, String contact ) {
    this.contactType = contactType;
    this.contact = contact;
  }

  @Column( name = "Contact_Type" )
  public String getContactType() {
    return this.contactType;
  }

  public void setContactType ( String contactType ) {
    this.contactType = contactType;
  }

  @Column( name = "Contact" )
  public String getContact() {
    return this.contact;
  }

  public void setContact ( String contact ) {
    this.contact = contact;
  }

  @Override
  public String toString() {
    return this.contactType + ": " + this.contact;
  }

  @Override
    public boolean equals(Object obj) {
       if(obj == null || getClass() != obj.getClass())
         return false;

        Contact tmp = (Contact) obj;

         return this.contactType.equals(tmp.getContactType())
         && this.contact.equals(tmp.getContact());

   }

   @Override
    public int hashCode() {
        return java.util.Objects.hash(contactType, contact);
    }

}
