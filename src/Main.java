import exceptionpackage.DuplicateException;
import exceptionpackage.NegativeAmountException;
import exceptionpackage.NotEnoughMoneyException;
import exceptionpackage.PlayerNotFoundException;

public class Main {
    public static void main(String[] args) {
        AccountManagerImpl acc = new AccountManagerImpl();
        StockPriceProvider spp = acc.getStockPriceProvider();

        System.out.println("Trying to create Players");
        try {
            acc.createPlayer("Peter");
            acc.createPlayer("Klaus");
            acc.createPlayer("Peter"); // exceptiontest
            System.out.println("finished!");
        } catch (DuplicateException e) {
            e.printStackTrace();
            System.out.println("> Wanted Exception! Continuing...\n");
        }

        // temporary give Players money for test purpose
        acc.setCashAccountStatus("Peter", 50000000l);
        acc.setCashAccountStatus("Klaus", 10000000l);

        System.out.println("Trying to create Shares");
        try {
            spp.createShare("BMW", 500);
            spp.createShare("Google", 70000);
            spp.createShare("VW", 100);
            System.out.println("finished!\n");
        } catch (DuplicateException e) {
            e.printStackTrace();
            System.out.println("> Wanted Exception! Continuing...\n");
        }

        System.out.println(acc.toString() + "\n");

        System.out.println("Trying to buy Shares");
        try {
            acc.buyShare("Klaus", "BMW", 100);
            acc.buyShare("Peter", "VW", 700000);
            System.out.println("finished!\n");
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
            System.out.println("> Wanted Exception! Continuing...\n");
        }

        System.out.println("Trying to sell Shares");
        try {
            acc.sellShare("Klaus", "BMW", -8);
            acc.sellShare("PPPeter", "VW", 5);
            System.out.println("finished!\n");
        } catch (PlayerNotFoundException e) {
            e.printStackTrace();
            System.out.println("> Wanted Exception! Continuing...\n");
        } catch (NegativeAmountException e1) {
            e1.printStackTrace();
            System.out.println("> Wanted Exception! Continuing...\n");
        }

        System.out.println(acc.toString() + "\nfinished test!");
    }
}
