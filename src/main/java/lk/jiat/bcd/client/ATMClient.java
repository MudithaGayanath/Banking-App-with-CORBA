package lk.jiat.bcd.client;

import BankingApp.Account;
import BankingApp.AccountHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Scanner;

public class ATMClient {
    public static void main(String[] args) {
        ORB orb = ORB.init(args, null);
        try {
            Object object = orb.resolve_initial_references("NameService");
            NamingContextExt namingContext = NamingContextExtHelper.narrow(object);


            Account bank = AccountHelper.narrow(namingContext.resolve_str("Bank"));
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter account no : ");
            String accNo = scanner.next();

            boolean running = true;

            while (running) {
                System.out.println("Options 1:Check balance | 2:Deposit | 3:" +
                        "withdraw | 4:Exit");
                int choies = scanner.nextInt();

                switch (choies) {
                    case 1:
                        System.out.println("Balance : " + bank.getBalance(accNo));
                        break;
                    case 2:
                        double value = scanner.nextDouble();
                        bank.deposit(accNo, value);
                        break;
                    case 3:
                        double amount = scanner.nextDouble();
                        bank.withdraw(accNo, amount);
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid choice");
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
