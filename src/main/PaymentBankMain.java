package main;

import java.util.Scanner;

public class PaymentBankMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PaymentBank bank = new PaymentBank();

        while(true){
            String command = sc.nextLine();
            String[] tokens = command.split(" ");

            if(tokens[0].equalsIgnoreCase("create")){
                StringBuilder fullName = new StringBuilder();
                for(int i=1;i< tokens.length;i++){
                    fullName.append(tokens[i]);
                    fullName.append(" ");
                }

                int accountNumber = bank.createAccount(fullName.toString().trim());
                System.out.println("Account created successfully. Account Number -> "+ accountNumber + " | Account Holder Name -> "+ bank.getAccountHolder(accountNumber));
            }
            else if(tokens[0].equalsIgnoreCase("deposit")){
                int accountNumber = Integer.parseInt(tokens[1]);
                double amount = Double.parseDouble(tokens[2]);

                bank.deposit(accountNumber,amount);
                System.out.println("Account Number -> "+ accountNumber +" | Current Balance -> "+ bank.getBalance(accountNumber));
            }
            else if(tokens[0].equalsIgnoreCase("withdraw")){
                int accountNumber = Integer.parseInt(tokens[1]);
                double amount = Double.parseDouble(tokens[2]);

                bank.withdraw(accountNumber,amount);
                System.out.println("Account Number -> "+ accountNumber +" | Current Balance -> "+ bank.getBalance(accountNumber));
            }
            else if(tokens[0].equalsIgnoreCase("balance")){
                int accountNumber = Integer.parseInt(tokens[1]);

                String balance = bank.getBalance(accountNumber);
                System.out.println("Current Balance - "+balance);
            } else if (tokens[0].equalsIgnoreCase("transfer")) {
                int sourceAccount = Integer.parseInt(tokens[1]);
                int targetAccount = Integer.parseInt(tokens[2]);
                double amount = Double.parseDouble(tokens[3]);

                bank.transfer(sourceAccount,targetAccount,amount);
            } else if(tokens[0].equalsIgnoreCase("exit")){
                break;
            }
            else{
                System.out.println("Invalid Command");
            }
        }
        sc.close();
    }
}
