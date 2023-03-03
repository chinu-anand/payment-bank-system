package test;

import main.PaymentBank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentBankTest {
    PaymentBank bank = new PaymentBank();
    @Test
    public void testCreateAccount() {
        Assertions.assertEquals(bank.createAccount("Amit Dugal"),1001);
        Assertions.assertEquals(bank.createAccount("Gauri Kalla"),1002);
    }

    @Test
    public void testDeposit(){
        testCreateAccount();
        Assertions.assertEquals(bank.deposit(1001,500),"500.0");
        Assertions.assertEquals(bank.deposit(1001,1000),"1500.0");
        Assertions.assertEquals(bank.deposit(1001,100),"Minimum deposit amount is 500");
        Assertions.assertEquals(bank.deposit(1001,60000),"Maximum deposit amount is 50000");
        Assertions.assertEquals(bank.deposit(1001,10000),"11500.0");
        Assertions.assertEquals(bank.deposit(1001,10000),"Only 3 deposits are allowed in a day");
    }


    @Test
    public void testGetBalance(){
        testDeposit();
        Assertions.assertEquals(bank.getBalance(1001),"11500.0");
        Assertions.assertEquals(bank.getBalance(1002),"0.0");
    }

    @Test
    public void testWithdraw(){
        testDeposit();
        Assertions.assertEquals(bank.withdraw(1001,500),"Minimum withdrawal amount is 1000");
        Assertions.assertEquals(bank.withdraw(1001,20000),"Insufficient Balance");
        Assertions.assertEquals(bank.withdraw(1001,1000),"10500.0");
        Assertions.assertEquals(bank.withdraw(1001,1900),"8600.0");
        Assertions.assertEquals(bank.withdraw(1001,1000),"7600.0");
        Assertions.assertEquals(bank.withdraw(1001,5000),"Only 3 withdrawals are allowed in a day");
    }

    @Test
    public void testTransfer(){
        testDeposit();
        Assertions.assertEquals(bank.transfer(1001,1002,5000),"Successful");
        bank.createAccount("John");
        bank.createAccount("Doe");
        Assertions.assertEquals(bank.transfer(1002,1004,500),"Minimum withdrawal amount is 1000 for account 1002");
        Assertions.assertEquals(bank.transfer(1002,1004,30000),"Maximum withdrawal amount is 25000 for account 1002");
        Assertions.assertEquals(bank.transfer(1002,1005,30000),"Invalid account number.");
    }
}
