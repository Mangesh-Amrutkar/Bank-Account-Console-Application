package dao;

import model.Account;
import dao.DBConnection;

import java.sql.*;

public class AccountDAO {
    public void createAccount(Account account) {
        String query = "INSERT INTO Account (account_holder_name, balance) VALUES (?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account.getAccountHolderName());
            statement.setDouble(2, account.getBalance());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccount(int accountNumber) {
        String query = "SELECT * FROM Account WHERE account_number = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Account(
                        resultSet.getInt("account_number"),
                        resultSet.getString("account_holder_name"),
                        resultSet.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAccount(Account account) {
        String query = "UPDATE Account SET account_holder_name = ?, balance = ? WHERE account_number = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account.getAccountHolderName());
            statement.setDouble(2, account.getBalance());
            statement.setInt(3, account.getAccountNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(int accountNumber) {
        String query = "DELETE FROM Account WHERE account_number = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
