package edu.calpoly.csc365.examples.webapp.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoManagerFactory {
  public static DaoManager createDaoManager() throws NamingException {
    Context initContext = new InitialContext();
    Context envContext  = (Context)initContext.lookup("java:comp/env");
    // change the jdbc/dbname to the resource name you registered in webapp/META-INF/context.xml
    DataSource ds = (DataSource)envContext.lookup("jdbc/dbname");
    return new DaoManager(ds);
  }
}
