import exceptionpackage.DuplicateException;
import exceptionpackage.NotEnoughMoneyException;

/*
 * Main ist die Testklasse
 */
public class Main {
    public static void main(String[] args) throws NotEnoughMoneyException, DuplicateException {
        int testcase = 3;
        switch (testcase) {
            case 1:
                testcase1();
                break;
            case 2:
                testcase2();
                break;
            case 3:
                testcase3();
                break;
            case 4:
                exceptionCase();
                break;
            case 5:
                buyShareTestcase();
            default:
                break;
        }
    }

    private static void testcase1() throws NotEnoughMoneyException {
        Player pl1 = new Player("Peter");
        Player pl2 = new Player("Klaus");

        Share share1 = new Share("BMW", 5000);
        Share share2 = new Share("VW", 1400);
        Share share3 = new Share("Google", 63300);

        pl1.getAccount().setAccountStatus(500000000l);
        pl2.getAccount().setAccountStatus(34895845l);
        System.out.println(pl1.getAccount().toString());
        System.out.println(pl1.getAccount().toString() + "\n");

        pl1.buy(share1, 100l);
        pl1.buy(share2, 230l);
        pl1.buy(share3, 422l);

        System.out.println("\nNach dem Kauf: ");
        System.out.println(pl1.toString());

        pl1.sell(share2, 230l);
        System.out.println("\nNach dem Kauf: ");
        System.out.println(pl1.toString());

        pl2.buy(share1, 210l);
        System.out.println(pl2.toString() + "\n");

        /**
         * 1
         * 
         */

        System.out.println(pl1.toString());
        System.out.println(pl2.toString());
    }

    private static void testcase2() throws NotEnoughMoneyException {
        Player pl1 = new Player("Peter");
        Share BMW = new Share("BMW", 100);
        pl1.getAccount().setAccountStatus(500000000000l);

        pl1.buy(BMW, 100l);
        System.out.println(pl1.getDeposit().toString());

        BMW.setRate(150);

        pl1.sell(BMW, 90);

        System.out.println(pl1.getDeposit().toString());

    }

    private static void testcase3() throws DuplicateException {
        AccountManagerImpl acc2 = new AccountManagerImpl();
        AccountManager acc = (AccountManager) acc2;

        acc.createPlayer("Peter");
        acc.createPlayer("Klaus");

        acc2.setCashAccountStatus("Peter", 5000000000l);
        acc2.setCashAccountStatus("Klaus", 10000000);

        acc.createShare("BMW", 500);
        acc.createShare("Google", 70000);
        acc.createShare("VW", 100);

        acc.buyShare("Peter", "Google", 10);
        acc.buyShare("Klaus", "BMW", 100);

        System.out.println(acc2.toString());

        acc.sellShare("Peter", "Google", 5);

        System.out.println(acc2.toString());

        System.out.println(acc.getAllAssetsValue("Peter"));
        System.out.println(acc.getShareRate("VW"));
        System.out.println('\r' + "Peter chashAccountStatus: " + acc.getAssetValue("Peter", "cashAccount"));
        System.out.println("Peter depositStatus: " + acc.getAssetValue("Peter", "deposit"));
        System.out.println("Peter google shareItem: " + acc.getAssetValue("Peter", "Google") + '\r');

        System.out.println(acc.allShares());

    }

    private static void exceptionCase() throws DuplicateException {
        AccountManagerImpl acc = new AccountManagerImpl();

        acc.createPlayer("Peter");

        String playerName = "Peter";
        try {
            acc.createPlayer(playerName); /* Duplicate Exception */
        } catch (DuplicateException e) {
            acc.createPlayer(playerName + "1");
            System.out.println("Runtime exception catched and handled!");
        }

        acc.createPlayer("Klaus");

        acc.setCashAccountStatus("Peter", 5000000000l);
        acc.setCashAccountStatus("Klaus", 10000000);

        acc.createShare("BMW", 500);
        acc.createShare("Google", 70000);
        acc.createShare("VW", 100);

        acc.buyShare("Peter", "Google", 1000000000);
        acc.buyShare("Klasdfus", "BMW", 100); /* NotFoundException */

        acc.sellShare("Peter", "Gosdfogle", 5); /* NotFoundException */
    }

    public static void buyShareTestcase() throws DuplicateException {
        AccountManagerImpl acc2 = new AccountManagerImpl();
        AccountManager acc = (AccountManager) acc2;

        acc.createPlayer("Peter");
        acc2.setCashAccountStatus("Peter", 500000);
        acc.createShare("Google", 60000);
        acc.buyShare("Peter", "Google", 5);

        System.out.println(acc2.toString());
    }
}
