package main;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private final Map<Integer, Account> accounts;

    public AccountRepository(){
        this.accounts = new HashMap<>();
    }

    public void addAccount(Account account){
        accounts.put(account.getAccountNumber(),account);
    }

    public Account getAccount(int accountNumber){
        return accounts.get(accountNumber);
    }
}
