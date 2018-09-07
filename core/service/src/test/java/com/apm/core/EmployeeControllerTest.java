package com.apm.core;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Set;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.MockitoAnnotations;

public class EmployeeControllerTest
{
  @Mock
  private Dao daoMock;
  @Mock
  private Employee employee;
  @Mock
  private Set<Roles> roles;
  @Mock
  private Set<Contact> contacts;
  @Mock
  private Contact contact;
  @Mock
  private Roles role;

  @Mock
  private EmployeeController employeeControllerMock;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testAddEmployee_verifyCall() {
    employeeControllerMock.addEmployee(employee.getEmployeeName(), employee.getBirthDate(), employee.getHireDate(),
    employee.getGwa(), employee.getIsCurrentlyHired(), employee.getAddress(), employee.getContacts(), employee.getRoles());
    verify ( employeeControllerMock, times(1)).addEmployee(any(EmployeeName.class), any(Date.class),
    any(Date.class), anyFloat(), anyBoolean(), any(Address.class), any(Set.class), any(Set.class));
  }

  // @Test
  // public void testDeleteRole_Success() throws Exception {
  //   String result = "Successfully Deleted";
  //   when(roleController.deleteRole(anyInt())).thenReturn("Successfully Deleted");
  //   assertTrue(roleController.deleteRole(5).equals(result));
  // }

  @Test ( expected = NullPointerException.class )
    public void testDeleteEmployee_NullPointerException() throws Exception {
      when ( employee.getEmployeeId() ).thenReturn(Long.valueOf(1));
      when ( employeeControllerMock.deleteEmployee ( employee.getEmployeeId().intValue()+1)).thenThrow(NullPointerException.class);
      employeeControllerMock.deleteEmployee(2);
    }

    @Test
    public void testDeleteEmployee_verifyCall() throws Exception {
      employeeControllerMock.deleteEmployee(1);
      verify ( employeeControllerMock, times(1)).deleteEmployee(anyInt());
    }

    @Test
    public void testDeletRetrieveAllEmployeeElementse_Success() throws Exception {
      String result = "Successfully Deleted";
      when ( employeeControllerMock.deleteEmployee(anyInt())).thenReturn("Successfully Deleted");
      assertTrue( employeeControllerMock.deleteEmployee(5).equals(result));
    }

    @Test
    public void testGetEmployee_verifyCall() throws Exception {
      employeeControllerMock.getEmployee(1);
      verify ( employeeControllerMock, times(1)).getEmployee(anyInt());
    }

    @Test ( expected = NullPointerException.class )
    public void testGetEmployee_employeeNotExists() throws Exception {
      when ( employeeControllerMock.getEmployee(0)).thenThrow(NullPointerException.class);
      employeeControllerMock.getEmployee(0);
    }

    @Test
    public void testGetEmployee_Success() throws Exception {
      when ( employeeControllerMock.getEmployee(anyInt())).thenReturn(employee);
      assertEquals ( employeeControllerMock.getEmployee(1), employee );
    }

    @Test ( expected = NullPointerException.class )
    public void testGetEmployee_contactsNull() throws Exception {
      when ( employee.getContacts() ).thenThrow(NullPointerException.class);
      employee.getContacts();
    }

    @Test ( expected = NullPointerException.class )
    public void testGetEmployee_rolesNull() throws Exception {
      when ( employee.getRoles() ).thenThrow(NullPointerException.class);
      employee.getRoles();
    }

    @Test
    public void testGetEmployeeWithContacts_verifyCall() throws Exception {
      employeeControllerMock.getEmployeeWithContacts(1);
      verify ( employeeControllerMock, times(1)).getEmployeeWithContacts(anyInt());
    }

    @Test ( expected = NullPointerException.class )
    public void testGetEmployeeWithContacts_employeeNotExists() throws Exception {
      when ( employeeControllerMock.getEmployeeWithContacts(0) ).thenThrow(NullPointerException.class);
      employeeControllerMock.getEmployeeWithContacts(0);
    }

    @Test
    public void testGetEmployeeWithContacts_Success() throws Exception {
      when ( employeeControllerMock.getEmployeeWithContacts(anyInt())).thenReturn(employee);
      assertEquals ( employeeControllerMock.getEmployeeWithContacts(1), employee );
    }

    @Test
    public void testGetEmployeeWithContacts_RolesSuccess() throws Exception {
      when ( employee.getContacts() ).thenReturn(contacts);
      assertEquals ( employee.getContacts(), contacts );
    }

    @Test
    public void testGetEmployeeWithRoles_verifyCall() throws Exception {
      employeeControllerMock.getEmployeeWithRoles(1);
      verify ( employeeControllerMock, times(1)).getEmployeeWithRoles(anyInt());
    }

    @Test ( expected = NullPointerException.class )
    public void testGetEmployeeWithRoles_employeeNotExists() throws Exception {
      when ( employeeControllerMock.getEmployeeWithRoles(0) ).thenThrow( NullPointerException.class );
      employeeControllerMock.getEmployeeWithRoles(0);
    }

    @Test
    public void testGetEmployeeWithRoles_Success() throws Exception {
      when ( employeeControllerMock.getEmployeeWithRoles(anyInt())).thenReturn(employee);
      assertEquals ( employeeControllerMock.getEmployeeWithRoles(1), employee);
    }

    @Test
    public void testGetEmployeeWithRoles_RolesSuccess() throws Exception {
      when ( employee.getRoles() ).thenReturn(roles);
      assertEquals ( employee.getRoles(), roles );
    }

    @Test
    public void testCreateEmployeeName() {
      EmployeeName employeeName = mock ( EmployeeName.class );
      when ( employeeControllerMock.createEmployeeName( anyString(), anyString(), anyString(), anyString(), anyString() ) ).thenReturn(employeeName);
      assertEquals ( employeeControllerMock.createEmployeeName( "Molina", "Adrian Paolo", "Mejillano", "", "" ), employeeName );
    }

    @Test
    public void testCreateNewAddress() {
      Address address = mock ( Address.class );
      when ( employeeControllerMock.createNewAddress( anyInt(), anyString(), anyString(), anyString(), anyString() ) ).thenReturn(address);
      assertEquals ( employeeControllerMock.createNewAddress(343, "Apitong", "Comembo", "Makati", "1207" ) , address );
    }

    @Test
    public void testCreateNewContact(){
      Contact contact = mock ( Contact.class );
      when ( employeeControllerMock.createNewContact( anyString(), anyString() ) ).thenReturn(contact);
      assertEquals ( employeeControllerMock.createNewContact("MOBILE", "0937-948-7937"), contact);
    }

    @Test
    public void testRetrieveAllEmployeeElements_verifyCall() {
      employeeControllerMock.retrieveAllEmployeeElements(Employee.class);
      verify ( employeeControllerMock, times(1)).retrieveAllEmployeeElements(any());
    }

    @Test
    public void testRetrieveAllEmployeeElements_Employee() {
      List list = mock (List.class);
      when ( employeeControllerMock.retrieveAllEmployeeElements ( any() ) ).thenReturn(list);
      assertEquals ( employeeControllerMock.retrieveAllEmployeeElements(Employee.class), list );
    }

    @Test
    public void testUpdateEmployeeElement_verifyCall() {
      employeeControllerMock.updateEmployeeElement(employee);
      verify ( employeeControllerMock, times(1)).updateEmployeeElement(any());
    }

    @Test ( expected = NullPointerException.class )
    public void testUpdateEmployeeElement_nullObject() {
      when ( employeeControllerMock.updateEmployeeElement(isNull(Employee.class))).thenThrow(NullPointerException.class);
      employeeControllerMock.updateEmployeeElement(null);
    }

    @Test
    public void testUpdateEmployeeElement_Success() {
      String result = "Update Successful";
      when ( employeeControllerMock.updateEmployeeElement(any(Employee.class))).thenReturn ("Update Successful");
      assertTrue ( employeeControllerMock.updateEmployeeElement(employee).equals(result));
    }

    @Test
    public void getNewEmployeeRoles_verifyCall() throws Exception {
      employeeControllerMock.getNewEmployeeRoles ( employee, 1 );
      verify ( employeeControllerMock, times(1)).getNewEmployeeRoles(any(Employee.class), anyInt());
    }

    @Test
    public void getNewEmployeeRoles_success() throws Exception {
      when ( employeeControllerMock.getNewEmployeeRoles( any(Employee.class), anyInt() ) ).thenReturn(employee);
      assertEquals ( employeeControllerMock.getNewEmployeeRoles(employee, 1), employee);
    }

    @Test ( expected = NullPointerException.class )
    public void getNewEmployeeRoles_RoleNotExists() throws Exception {
      when ( employeeControllerMock.getNewEmployeeRoles( employee, 0 ) ).thenThrow(NullPointerException.class);
      employeeControllerMock.getNewEmployeeRoles( employee , 0);
    }

    @Test ( expected = NullPointerException.class )
    public void getNewEmployeeRoles_EmployeeNotExists() throws Exception {
      when ( employeeControllerMock.getNewEmployeeRoles( isNull(Employee.class), anyInt() ) ).thenThrow(NullPointerException.class);
      employeeControllerMock.getNewEmployeeRoles(null, 1);
    }

    @Test
    public void testRemoveEmployeeRole_verifyCall() throws Exception {
      employeeControllerMock.removeEmployeeRole(employee, 1);
      verify ( employeeControllerMock, times(1)).removeEmployeeRole(any(Employee.class), anyInt());
    }

    @Test
    public void testRemoveEmployeeRole_success() throws Exception {
      when ( employeeControllerMock.removeEmployeeRole(any(Employee.class), anyInt())).thenReturn(employee);
      assertEquals ( employeeControllerMock.removeEmployeeRole(employee, 1), employee);
    }

    @Test ( expected = NullPointerException.class )
    public void testRemoveEmployeeRole_EmployeeNotExists() throws Exception {
      when ( employeeControllerMock.removeEmployeeRole(isNull(Employee.class), anyInt())).thenThrow(NullPointerException.class);
      employeeControllerMock.removeEmployeeRole(null, 1);
    }

    @Test
    public void testAddContact() {
      when ( employeeControllerMock.addContact( any(Employee.class), anySet() ) ).thenReturn(employee);
      assertEquals ( employeeControllerMock.addContact( employee, contacts ), employee );
    }

}
