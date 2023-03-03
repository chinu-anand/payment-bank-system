package main;

public class PaymentBank {
    private final AccountRepository accountRepository;
    private int nextAccountNumber;

    public PaymentBank(){
        this.accountRepository = new AccountRepository();
        nextAccountNumber = 1001;
    }

    public int createAccount(String accountHolderName){
        Account account = new Account(nextAccountNumber,accountHolderName);
        accountRepository.addAccount(account);
        nextAccountNumber++;
        return account.getAccountNumber();
    }

    public String deposit(int accountNumber, double amount) {
        Account account = accountRepository.getAccount(accountNumber);

        if(account == null){
            return "Invalid Account Number";
        }
        return account.deposit(amount);
    }

    public String withdraw(int accountNumber, double amount){
        Account account = accountRepository.getAccount(accountNumber);

        if(account == null){
            return "Invalid Account Number";
        }
        return account.withdraw(amount);
    }

    public String transfer(int sourceAccountNumber, int targetAccountNumber, double amount) {
        Account sourceAccount = accountRepository.getAccount(sourceAccountNumber);
        Account targetAccount = accountRepository.getAccount(targetAccountNumber);

        if (sourceAccount == null || targetAccount == null) {
            return "Invalid account number.";
        }
        else if (sourceAccount == targetAccount) {
            return "Source and target account numbers cannot be the same.";
        }
        else if (amount < 1000) {
            return "Minimum withdrawal amount is 1000 for account "+sourceAccountNumber;
        }
        else if (amount > 25000) {
            return "Maximum withdrawal amount is 25000 for account "+sourceAccountNumber;
        }
        else if (sourceAccount.getBalance() - amount < 0) {
            return "Insufficient balance";
        }
        sourceAccount.withdraw(amount);
        targetAccount.deposit(amount);
        return "Successful";
    }

    public String getBalance(int accountNumber) {
        Account account = accountRepository.getAccount(accountNumber);

        if(account==null){
            return "Invalid Account Number.";
        }
        else{
            return Double.toString(account.getBalance());
        }

    }

    public String getAccountHolder(int accountNumber){
        Account account = accountRepository.getAccount(accountNumber);

        if(account==null){
            System.out.println("Invalid Account Number.");
            return "";
        }
        return account.getAccountHolderName();
    }

}
