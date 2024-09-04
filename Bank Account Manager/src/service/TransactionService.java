package service;

import dao.TransactionDAO;
import model.Transaction;

import java.sql.Timestamp;
import java.util.Date;

public class TransactionService {
    private TransactionDAO transactionDAO = new TransactionDAO();

    public void deposit(int accountNumber, double amount) {
        Transaction transaction = new Transaction(0, accountNumber, "Deposit", amount, new Timestamp(new Date().getTime()));
        transactionDAO.addTransaction(transaction);
    }

    public void withdraw(int accountNumber, double amount) {
        Transaction transaction = new Transaction(0, accountNumber, "Withdrawal", amount, new Timestamp(new Date().getTime()));
        transactionDAO.addTransaction(transaction);
    }

    public void transfer(int fromAccount, int toAccount, double amount) {
        Transaction withdrawTransaction = new Transaction(0, fromAccount, "Transfer Out", amount, new Timestamp(new Date().getTime()));
        Transaction depositTransaction = new Transaction(0, toAccount, "Transfer In", amount, new Timestamp(new Date().getTime()));
        transactionDAO.addTransaction(withdrawTransaction);
        transactionDAO.addTransaction(depositTransaction);
    }
}
