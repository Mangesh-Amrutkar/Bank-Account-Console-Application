package dao;

import model.Transaction;
import dao.DBConnection;

import java.sql.*;

public class TransactionDAO {
    public void addTransaction(Transaction transaction) {
        String query = "INSERT INTO Transaction (account_number, transaction_type, amount, transaction_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, transaction.getAccountNumber());
            statement.setString(2, transaction.getTransactionType());
            statement.setDouble(3, transaction.getAmount());
            statement.setTimestamp(4, transaction.getTransactionDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
