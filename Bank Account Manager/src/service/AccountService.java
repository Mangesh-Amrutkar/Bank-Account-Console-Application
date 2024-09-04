package service;

import dao.AccountDAO;
import model.Account;

public class AccountService {
    private AccountDAO accountDAO = new AccountDAO();

    public void createAccount(String holderName, double initialBalance) {
        Account account = new Account(0, holderName, initialBalance);
        accountDAO.createAccount(account);
    }

    public Account getAccount(int accountNumber) {
        return accountDAO.getAccount(accountNumber);
    }

    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }

    public void deleteAccount(int accountNumber) {
        accountDAO.deleteAccount(accountNumber);
    }
}
