package animalpackage;

import assetpackage.CashAccount;
import assetpackage.ShareDeposit;

public class Player {
    private CashAccount acc;
    private ShareDeposit depo;
    private String name;

    /**
     * Player(String name)
     */
    public Player(String name) {
        this.acc = new CashAccount(name);
        this.depo = new ShareDeposit(name);
        this.name = name;
        
        //jeder Spieler bekommt ein Startkapital von 1000 Euro
        acc.addMoney(100000);
    }

    /**
     * getAccount()
     */
    public CashAccount getAccount() {
        return acc;
    }

    /**
     * getDeposit()
     */
    public ShareDeposit getDeposit() {
        return depo;
    }

    /**
     * getName()
     */
    public String getName() {
        return name;
    }

    /**
     * toString()
     */
    public String toString() {
        return ("Der Spieler " + name + " besitzt: \n" + depo.toString() + " und hat " + acc.toString());
    }
}