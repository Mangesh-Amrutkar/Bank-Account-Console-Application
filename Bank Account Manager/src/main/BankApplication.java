package main;

import model.Account;
import service.AccountService;
import service.TransactionService;

import java.util.Scanner;

public class BankApplication {
    private static AccountService accountService = new AccountService();
    private static TransactionService transactionService = new TransactionService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. View Account Details");
            System.out.println("3. Update Account Information");
            System.out.println("4. Close Account");
            System.out.println("5. Deposit Funds");
            System.out.println("6. Withdraw Funds");
            System.out.println("7. Transfer Funds");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    viewAccountDetails();
                    break;
                case 3:
                    updateAccountInformation();
                    break;
                case 4:
                    closeAccount();
                    break;
                case 5:
                    depositFunds();
                    break;
                case 6:
                    withdrawFunds();
                    break;
                case 7:
                    transferFunds();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String holderName = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        accountService.createAccount(holderName, initialBalance);
        System.out.println("Account created successfully.");
    }

    private static void viewAccountDetails() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Account account = accountService.getAccount(accountNumber);
        if (account != null) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder Name: " + account.getAccountHolderName());
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void updateAccountInformation() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Account account = accountService.getAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter new account holder name: ");
            String holderName = scanner.nextLine();
            System.out.print("Enter new balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            account.setAccountHolderName(holderName);
            account.setBalance(balance);
            accountService.updateAccount(account);
            System.out.println("Account updated successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void closeAccount() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        accountService.deleteAccount(accountNumber);
        System.out.println("Account closed successfully.");
    }

    private static void depositFunds() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        transactionService.deposit(accountNumber, amount);
        System.out.println("Funds deposited successfully.");
    }

    private static void withdrawFunds() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        transactionService.withdraw(accountNumber, amount);
        System.out.println("Funds withdrawn successfully.");
    }

    private static void transferFunds() {
        System.out.print("Enter source account number: ");
        int fromAccount = scanner.nextInt();
        System.out.print("Enter destination account number: ");
        int toAccount = scanner.nextInt();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        transactionService.transfer(fromAccount, toAccount, amount);
        System.out.println("Funds transferred successfully.");
    }
}
