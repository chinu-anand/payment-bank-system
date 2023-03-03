package main;

import java.time.LocalDate;

public class Account {
    private final int accountNumber;
    private final String accountHolderName;
    private double balance;
    private int depositCount;
    private int withdrawalCount;
    private LocalDate lastTransactionDate;


    public Account(int accountNumber, String accountHolderName){
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0;
        this.depositCount = 0;
        this.withdrawalCount = 0;
    }

    public String getAccountHolderName(){
        return accountHolderName;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public double getBalance(){
        return balance;
    }

    public String deposit(double amount) {
        // reset deposit and withdrawal limits on a new day
        LocalDate today = LocalDate.now();
        if(lastTransactionDate!=null && !today.isEqual(lastTransactionDate)){
            depositCount = 0;
            withdrawalCount = 0;
        }

        // setting limit for deposits
        else if(amount<500){
            return "Minimum deposit amount is 500";
        }
        else if(amount>50000){
            return "Maximum deposit amount is 50000";
        }
        else if(depositCount>=3){
            return "Only 3 deposits are allowed in a day";
        }

        else if(balance+amount>100000){
            return "Account balance cannot exceed 1,00,000.";
        }

        else {
            balance += amount;
            depositCount++;
            lastTransactionDate = today;
        }
        return Double.toString(balance);
    }

    public String withdraw(double amount) {
        // reset deposit and withdrawal limits on a new day
        LocalDate today = LocalDate.now();
        if(lastTransactionDate!=null && !today.isEqual(lastTransactionDate)){
            depositCount = 0;
            withdrawalCount = 0;
        }

        // setting limit for deposits
        else if(amount<1000){
            return "Minimum withdrawal amount is 1000";
        }
        else if(amount>25000){
            return "Maximum withdrawal amount is 25000";
        }

        else if(withdrawalCount>=3){
            return "Only 3 withdrawals are allowed in a day";
        }

        else if(balance-amount<0){
            return "Insufficient Balance";
        }

        else {
            balance -= amount;
            withdrawalCount++;
            lastTransactionDate = today;
        }

        return Double.toString(balance);
    }
}
