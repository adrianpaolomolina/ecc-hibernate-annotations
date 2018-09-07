package com.apm.core;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import java.util.List;
import com.apm.core.Employee;


public class EmployeeDao extends Dao
{

  private final Statistics stats = DaoConfiguration.getSessionFactory().getStatistics();

  public void saveEmployee ( Employee employee ) {
    save ( employee );
  }

  public String deleteEmployee ( Employee employee ) {
    return employee.getEmployeeName() + delete ( employee );
  }

  public Employee getSpecificEmployee ( Long id, Class<Employee> employee ) {
    return getSpecific ( id, employee );
  }

  public Employee getSpecificWithChild ( final long id, final Class<Employee> employeeClass, String type ) {
		Session session = startSession();
		Employee employee = ( Employee ) session.get ( employeeClass, id );
    if ( type.equals("CONTACTS") ) {
      Hibernate.initialize(employee.getContacts());
    } else if ( type.equals("ROLES") ) {
      Hibernate.initialize(employee.getRoles());
    }
    session.close();
		return employee;
	}

  public List<Employee> getEmployeeSortByLastName ( final Class<Employee> employee ) {
    String query = "FROM Employee e ORDER BY e.employeeName.lastName ";
    List<Employee> employees = getByQuery ( query, employee );
    return employees;
  }

  public void updateEmployee ( Employee employee ) {
    update(employee);
  }

  public List<Employee> getEmployeeSortByHireDate ( final Class<Employee> employee ) {
    Session session = startSession();
    Criteria employeeSortByHireDate = session.createCriteria(employee)
                                          .addOrder( Order.asc("hireDate") )
                                          .setCacheable(true);
    List<Employee> employees = employeeSortByHireDate.list();
    session.close();
    return employees;
  }

  public List<Employee> retrieveAllEmployees ( final Class<Employee> employee ) {
    return getAll(employee);
  }

  public List<Object[]> getAllEmployeesWithChild ( Class<Employee> employee, String type ){
    Session session = startSession();
    Criteria employeesCriteria = session.createCriteria(employee, "employee");
    ProjectionList projectionList = Projections.projectionList();
    projectionList.add(Projections.property("employeeId"), "employeeId");
    projectionList.add(Projections.property("employeeName.lastName"), "lastName");
    projectionList.add(Projections.property("employeeName.firstName"), "firstName");
    projectionList.add(Projections.property("employeeName.middleName"), "middleName");
    if ( type.equals("CONTACTS") ) {
      employeesCriteria.createAlias("employee.contacts", "contacts");
      projectionList.add(Projections.property("contacts.contactType"), "contactType");
      projectionList.add(Projections.property("contacts.contact"), "contact");
    } else if ( type.equals("ROLES") ) {
      employeesCriteria.createAlias("employee.roles", "roles");
      projectionList.add(Projections.property("roles.roleID"), "roleID");
      projectionList.add(Projections.property("roles.role"), "role");
    }
    employeesCriteria.setProjection(projectionList).setCacheable(true);
    List<Object[]> employees = (List<Object[]>) employeesCriteria.list();
    session.close();
    return employees;
  }

  public static void showStatistics(String from, Statistics stats) {
      System.out.println("***********************************");
      System.out.println("Second level cache hit count : "+ stats.getSecondLevelCacheHitCount());
      System.out.println("Second level cache put count : " + stats.getSecondLevelCachePutCount());
      System.out.println("Second level cache miss count : " + stats.getSecondLevelCacheMissCount());
      System.out.println("***********************************");
  }

}
