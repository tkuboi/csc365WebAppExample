package edu.calpoly.csc365.examples.webapp.dao;

import edu.calpoly.csc365.examples.webapp.entity.Customer;

import javax.naming.NamingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DaoManager {

  private String driver = null;
  private String url = null;
  private String user = null;
  private String pass = null;

  //Private
  private Connection conn;
  private static DaoManager instance = null;

  private DaoManager() throws Exception {
    try
    {
      this.conn = ConnectionFactory.getConnection();
    }
    catch(Exception e) { throw e; }
  }

  public static DaoManager getInstance() throws Exception {
    if (DaoManager.instance == null) {
      DaoManager.instance = new DaoManager();
    }
    return DaoManager.instance;
  }

  public Connection getConnection() {
    return this.conn;
  }

  public void open() throws SQLException, NamingException {
    if(this.conn == null || this.conn.isClosed()) {
      this.conn = ConnectionFactory.getConnection();
    }
  }

  public void close() throws SQLException {
    try
    {
      if(this.conn != null && !this.conn.isClosed())
        this.conn.close();
    }
    catch(SQLException e) { throw e; }
  }

  public Object transaction(DaoCommand command){
    try{
      this.conn.setAutoCommit(false);
      Object returnValue = command.execute(this);
      this.conn.commit();
      return returnValue;
    } catch(Exception e){
      try {
        this.conn.rollback();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    } finally {
      try {
        this.conn.setAutoCommit(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public Dao<Customer> getCustomerDao() {
    return new CustomerDaoImpl(this.conn);
  }

}
