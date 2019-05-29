package edu.calpoly.csc365.examples.webapp.dao;

import edu.calpoly.csc365.examples.webapp.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CustomerDaoImpl implements Dao<Customer> {
  private Connection conn;

  public CustomerDaoImpl(Connection conn) {
    this.conn = conn;
  }

  @Override
  public Customer getById(int id) {
    return null;
  }

  @Override
  public Set<Customer> getAll() {
    Set<Customer> customers = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = this.conn.prepareStatement("SELECT * FROM Customer");
      resultSet = preparedStatement.executeQuery();
      customers = unpackResultSet(resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (resultSet != null)
          resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (preparedStatement != null)
          preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return customers;
  }

  @Override
  public Boolean insert(Customer obj) {
    return null;
  }

  @Override
  public Boolean update(Customer obj) {
    return null;
  }

  @Override
  public Boolean delete(Customer obj) {
    return null;
  }

  private Set<Customer> unpackResultSet(ResultSet rs) throws SQLException {
    Set<Customer> customers = new HashSet<Customer>();

    while(rs.next()) {
      Customer customer = new Customer(
        rs.getInt("id"),
        rs.getInt("ssn"),
        rs.getString("name"),
        rs.getString("address"),
        rs.getString("phone"));
      customers.add(customer);
    }
    return customers;
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
  }
}
