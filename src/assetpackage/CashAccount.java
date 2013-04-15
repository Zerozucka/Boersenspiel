package assetpackage;

import exceptionpackage.NotEnoughMoneyException;

public class CashAccount extends Asset {
    private long accountStatus = 0; // money on account

    /**
     * CashAccount(String name)
     */
    public CashAccount(String name) {
        super(name);
        this.name = name;
    }

    /**
     * getAccountStatus()
     */
    public long getAccountStatus() {
        return accountStatus;
    }

    /**
     * setAccountStatus(long accountStatus)
     */
    public void setAccountStatus(long accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * addMoney(long amount)
     *      adds amount of money
     */
    public void addMoney(long amount) {
        if (amount > 0)
            accountStatus += amount;
    }

    /**
     * subMoney(long amount)
     *      substract amount of money
     */
    public void subMoney(long amount) throws NotEnoughMoneyException {
        if (accountStatus - amount < 0)
            throw new NotEnoughMoneyException("Nicht genügend Geld vorhanden!");
        if (amount > 0)
            accountStatus -= amount;
    }

    /**
     * toString()
     */
    public String toString() {
        return ("Akutelles Guthaben von " + name + ": " + accountStatus);
    }
}