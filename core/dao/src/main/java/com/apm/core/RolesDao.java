package com.apm.core;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.Criteria;
import org.hibernate.transform.*;
import java.util.List;
import com.apm.core.Employee;

public class RolesDao extends Dao {

  public void saveRole ( Roles role ) {
    save(role);
  }

  public String deleteRole ( Roles role ) {
    return delete(role);
  }

  public Roles getSpecificRole ( final long id, final Class<Roles> roles ) {
    return getSpecific(id, roles);
  }

  public Roles getRole ( Roles roles ) {
    return get(roles);
  }

  public void updateRole ( Roles roles ) {
    update(roles);
  }

  public List<Roles> retrieveAllRoles ( final Class<Roles> roles ) {
    return getAll(roles);
  }

}
