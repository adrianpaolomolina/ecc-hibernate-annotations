package com.apm.core;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;
import com.apm.core.Employee;

public class Dao
{

  protected static Session startSession() {
    Session session = DaoConfiguration.getSessionFactory().openSession();
    if ( !session.getTransaction().isActive() ) {
      session.beginTransaction();
    }
    return session;
  }

  protected <T> void save ( final T t ) {
    Session session = startSession();
    session.save(t);
    session.getTransaction().commit();
    session.close();
  }

  protected <T> String delete ( final T t ) {
    Session session = startSession();
    session.delete ( t );
    session.getTransaction().commit();
    session.close();
    return "Successfully Deleted!";
  }

  protected <T> T getSpecific ( final long id, final Class<T> type ) {
		Session session = startSession();
		T t = ( T ) session.get ( type, id );
		session.close();
		return t;
	}

  protected void closeSession() {
    Session session = DaoConfiguration.getSessionFactory().getCurrentSession();
    session.close();
  }

  protected <T> T get ( T t ) {
		Session session = startSession();
		List<T> list = session.createCriteria ( t.getClass() )
                  .setCacheable(true)
                  .list();
		session.getTransaction().commit();
		session.close();
		return (T) list.get ( list.indexOf ( (T) t) );
	}

  protected <T> List<T> getAll ( final Class<T> type ) {
	   	Session session = startSession();
	   	List<T> list = session.createCriteria ( type )
                  .setCacheable(true)
                  .list();
	   	session.close();
	   	return list;
 	}

  protected <T> void update ( final T t ) {
	  	Session session = startSession();
	   	session.saveOrUpdate ( t );
	   	session.getTransaction().commit();
	   	session.close();
	}

  protected <T> List<T> getByQuery ( String query, final Class<T> type ) {
	  	Session session = startSession();
	   	List<T> list = session.createQuery(query)
                    .setCacheable(true)
                    .list();
	   	session.close();
	   	return list;
  	}

}
