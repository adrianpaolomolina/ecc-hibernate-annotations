package com.apm.core;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Set;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.MockitoAnnotations;

public class RoleControllerTest {

  // @Mock
  // private Dao daoMock;
  // @Mock
  // private Employee employee;
  // @Mock
  // private Roles role;
  //
  // @InjectMocks
  // private RoleController roleController = mock ( RoleController.class );
  //
  // @Before
  // public void setUp() throws Exception {
  //   MockitoAnnotations.initMocks(this);
  // }
  //
  // @Test
  // public void testAddRole_verifyCall() throws Exception {
  //   roleController.addRole ( "CEO" );
  //   verify ( roleController , times(1)).addRole(anyString());
  // }
  //
  // @Test ( expected = Exception.class )
  // public void testAddRole_RoleExistsException() throws Exception {
  //   role = new Roles ("CEO");
  //   doThrow(Exception.class).when(roleController).addRole(role.getRole());
  //   roleController.addRole("CEO");
  // }
  //
  // @Test ( expected = NullPointerException.class )
  // public void testAddRole_NullPointerException() throws Exception {
  //   doThrow(NullPointerException.class).when(roleController).addRole(null);
  //   roleController.addRole(null);
  // }
  //
  // @Test
  // public void testDeleteRole_Success() throws Exception {
  //   String result = "Successfully Deleted";
  //   when(roleController.deleteRole(anyInt())).thenReturn("Successfully Deleted");
  //   assertTrue(roleController.deleteRole(5).equals(result));
  // }
  //
  // @Test ( expected = NullPointerException.class )
  // public void testDeleteRole_NullPointerException() throws Exception {
  //   when(role.getRoleID()).thenReturn(Long.valueOf(1));
  //   when(roleController.deleteRole(role.getRoleID().intValue()+1)).thenThrow(NullPointerException.class);
  //   roleController.deleteRole(2);
  // }
  //
  // @Test
  // public void testCreateNewRoles() {
  //   roleController = spy(new RoleController());
  //   when ( roleController.createNewRoles(anyString())).thenReturn(role);
  //   assertEquals ( roleController.createNewRoles("CEO"), role );
  // }
  //
  // @Test
  // public void testRetrieveRole_verifyCall() throws Exception {
  //   roleController.retrieveRole(1);
  //   verify ( roleController, times(1)).retrieveRole(anyInt());
  // }
  //
  // @Test ( expected = NullPointerException.class )
  // public void testRetrieveRole_NullPointerException() throws Exception {
  //   doThrow(NullPointerException.class).when(roleController).retrieveRole(anyInt());
  //   roleController.retrieveRole(1);
  // }
  //
  // @Test
  // public void testRetrieveRole_Success() throws Exception {
  //   when(roleController.retrieveRole(anyInt())).thenReturn(role);
  //   assertEquals ( roleController.retrieveRole(1), role);
  // }
  //
  // @Test
  // public void testEditRole_verifyCall() throws Exception {
  //   String roleName = "newRole" ;
  //   roleController.editRole( role, roleName );
  //   verify ( roleController, times(1)).editRole(any(Roles.class), anyString() );
  // }
  //
  // @Test ( expected = Exception.class )
  // public void testEditRole_RoleIsNull() throws Exception {
  //   doThrow(Exception.class).when(roleController).editRole(isNull(Roles.class), anyString() );
  //   roleController.editRole(null, "CEO");
  // }
  //
  // @Test ( expected = Exception.class )
  // public void testEditRole_RoleNameIsNull() throws Exception {
  //   doThrow(Exception.class).when(roleController).editRole(any(Roles.class), isNull(String.class));
  //   roleController.editRole(role, null);
  // }
  //
  // @Test ( expected = Exception.class )
  // public void testEditRole_BothRoleAndNameNull() throws Exception {
  //   doThrow(Exception.class).when(roleController).editRole(isNull(Roles.class), isNull(String.class));
  //   roleController.editRole(null,null);
  // }
  //
  // @Test
  // public void testEditRole_Success() throws Exception {
  //   String expected = "Successfully Updated";
  //   when(roleController.editRole(any(Roles.class),anyString())).thenReturn("Successfully Updated");
  //   assertEquals ( expected, roleController.editRole(role, "CEO") );
  // }

}
