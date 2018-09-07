package com.apm.core;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DaoConfiguration {

  private static final SessionFactory sessionFactory;

  static {
    try {
      sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
    } catch ( Throwable ex ) {
      System.err.println ( "Failed to create Session Factory!" + ex );
      throw new ExceptionInInitializerError( ex );
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

}
