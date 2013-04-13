import exceptionpackage.NotEnoughMoneyException;

public class Player {
    private CashAccount acc;
    private ShareDeposit depo;
    private String name;

    /*
     * Constructor
     */
    public Player(String name) {
        this.acc = new CashAccount(name);
        this.depo = new ShareDeposit(name);
        this.name = name;
    }

    /*
     * getAccount();
     */
    public CashAccount getAccount() {
        return acc;
    }

    /*
     * getDeposit();
     */
    public ShareDeposit getDeposit() {
        return depo;
    }

    /*
     * getName();
     */
    public String getName() {
        return name;
    }

    /*
     * toString();
     */
    public String toString() {
        return ("Der Spieler " + name + " besitzt: \n" + depo.toString() + " Aktien und hat " + acc.toString());
    }

    /*
     * Buy/Sell methods
     * 
     * buy(Share share, long amount); kauft angegebene anzahl an shares und bucht Wert vom Spielerkonto ab
     */
    public void buy(Share share, long amount) throws NotEnoughMoneyException {
        System.out.println(acc.getName() + " will eine Aktie von " + share.getName() + " kaufen!");
        long diff = share.getRate() * amount;
        long money = acc.getAccountStatus();
        if (money - diff <= 0)
            throw new NotEnoughMoneyException("Nicht genug Geld vorhanden!");
        else {
            depo.addShare(share, amount);
            acc.setAccountStatus(money - diff);
        }
    }

    /*
     * sell(Share share, long amount); verkauft angegebene Anzahl und f�gt Verkaufswert dem Spielerkonto hinzu
     */
    public void sell(Share share, long amount){
        System.out.println(acc.getName() + " will eine Aktie von " + share.getName() + " verkaufen!");
        depo.deleteShare(share, amount);

        long diff = share.getRate() * amount;
        long money = acc.getAccountStatus();
        acc.setAccountStatus(money + diff);
    }
}