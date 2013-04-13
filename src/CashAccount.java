/*
 * CashAccount ist für die Implementierung des Geldkontos von mehreren Benutzern zuständig
 */
public class CashAccount extends Asset {
    private long accountStatus = 0; // money on account

    /*
     * Konstruktor
     */
    public CashAccount(String name) {
        super(name);
        this.name = name;
    }

    /*
     * getAccountStatus()
     */
    public long getAccountStatus() {
        return accountStatus;
    }

    /*
     * setAccountStatus(long accountStatus)
     */
    public void setAccountStatus(long accountStatus) {
        this.accountStatus = accountStatus;
    }

    /*
     * toString()
     */
    public String toString() {
        return ("Akutelles Guthaben von " + name + ": " + accountStatus);
    }
}