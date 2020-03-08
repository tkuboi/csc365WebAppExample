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
    Customer customer = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = this.conn.prepareStatement("SELECT * FROM Customer WHERE id=?");
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();
      Set<Customer> customers = unpackResultSet(resultSet);
      customer = (Customer)customers.toArray()[0];
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
    return customer;
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
    try {
      PreparedStatement preparedStatement = this.conn.prepareStatement(
        "UPDATE Customer SET ssn=?, name=?, address=?, phone=? WHERE id=?");
      preparedStatement.setString(1, obj.getSsn());
      preparedStatement.setString(2, obj.getName());
      preparedStatement.setString(3, obj.getAddress());
      preparedStatement.setString(4, obj.getPhone());
      preparedStatement.setInt(5, obj.getId());
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
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
        rs.getString("ssn"),
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
