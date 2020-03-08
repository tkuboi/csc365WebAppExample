package edu.calpoly.csc365.examples.webapp.dao;

import edu.calpoly.csc365.examples.webapp.entity.Transaction;

import java.sql.*;

public class TransactionDaoCommandImpl implements DaoCommand {
  private Transaction transaction;

  public TransactionDaoCommandImpl(Transaction transaction) {
    this.transaction = transaction;
  }

  @Override
  public Object execute(DaoManager daoManager) {
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Integer rowsAffected = 0;
    try {
      conn = daoManager.getConnection();
      preparedStatement = conn.prepareStatement(
        "INSERT INTO Transaction (cid, card_number, vid, amount, tdate) VALUES (?, ?, ?, ?, ?)",
        Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setInt(1, transaction.getCustomerId());
      preparedStatement.setInt(2, transaction.getCardNumber());
      preparedStatement.setInt(3, transaction.getVendorId());
      preparedStatement.setDouble(4, transaction.getAmount());
      preparedStatement.setDate(5, transaction.getDate());
      rowsAffected = preparedStatement.executeUpdate();
      if (rowsAffected > 0) {
        resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next())
          transaction.setId(resultSet.getInt(1));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("Error occurred during SQL execution!");
    }
    return transaction;
  }
}
