package lk.jiat.bcd.server;

import BankingApp.AccountPOA;
import BankingApp.InsufficientBalance;
import BankingApp.NoAccountFound;

import java.util.HashMap;

public class AccountImpl extends AccountPOA {

    private HashMap<String,Double> db = new HashMap();

    public AccountImpl() {
        initDb();
    }

    private void initDb() {
        System.out.println("Initializing accounts...");
        for (int i = 0; i < 5; i++) {
            db.put("ACC00"+i,0.0);
        }
    }


    @Override
    public double getBalance(String accNo) throws NoAccountFound {
        Double balance = db.get(accNo);
        if (balance == null) {
            throw new NoAccountFound("No account with account no: "+accNo);
        }
        return balance;

    }

    @Override
    public void deposit(String accNo, double amount) throws NoAccountFound{



        Double currentBalance = db.get(accNo);
        if (currentBalance == null) {
            throw new NoAccountFound("No account with account no: "+accNo);
        }
        db.put(accNo,currentBalance+amount);
        System.out.println("Server log : LKR:"+amount+" deposited to account "+accNo);
    }

    @Override
    public void withdraw(String accNo, double amount) throws InsufficientBalance , NoAccountFound {
        Double current = db.getOrDefault(accNo, 0.0);
        if(current < amount){
            System.out.println("Server log : Failed withdrawal requested amount...");
            throw new InsufficientBalance("Insufficient Balance");
        }

        db.put(accNo,current-amount);
        System.out.println("Server log : LKR:"+amount+" deposited to account "+accNo);
    }
}
