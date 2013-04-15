import animalpackage.Player;
import animalpackage.Share;
import exceptionpackage.DuplicateException;
import exceptionpackage.NegativeAmountException;
import exceptionpackage.NotEnoughMoneyException;
import exceptionpackage.PlayerNotFoundException;

public class AccountManagerImpl implements AccountManager {

    private Player[] playerArray = new Player[10];
    private StockPriceProvider spp = null;
    
    public AccountManagerImpl() {
        this.spp = new StockPriceProvider();
    }

    /**
     * getStockPriceProvider()
     */
    public StockPriceProvider getStockPriceProvider() {
        return spp;
    }
    
    /**
     * buyShare(String playerName, String shareName, long amount)
     * @throws NotEnoughMoneyException 
     */
    public void buyShare(String playerName, String shareName, long amount) throws NotEnoughMoneyException {
        Player foundPlayer = findPlayer(playerName);
        Share foundShare = spp.findShare(shareName);
        
        if (amount < 0)
            throw new NegativeAmountException("Negativer Amount nicht möglich");
        System.out.println(foundPlayer.getName() + " will eine Aktie von " + foundShare.getName() + " kaufen!");
        
        long cost = foundShare.getRate() * amount;
        
        foundPlayer.getAccount().subMoney(cost);
        foundPlayer.getDeposit().addShare(foundShare, amount);
    }
    
    /**
     * sellShare(String playerName, String shareName, long amount) 
     */
    public void sellShare(String playerName, String shareName, long amount) {
        Player foundPlayer = findPlayer(playerName);
        Share foundShare = spp.findShare(shareName);
        
        if (amount < 0)
            throw new NegativeAmountException("Negativer Amount nicht möglich");
        System.out.println(foundPlayer.getName() + " will eine Aktie von " + foundShare.getName() + " verkaufen!");

        long cost = foundShare.getRate() * amount;
        foundPlayer.getDeposit().deleteShare(foundShare, amount);
        foundPlayer.getAccount().addMoney(cost);
    }
    
    /**
     * createPlayer(String name)
     *      ueberprueft, ob schon ein Spieler mit dem Namen existiert, wenn das der Fall ist wird eine DuplicationException geworfen
     */
    public void createPlayer(String name) throws DuplicateException {
        if (!checkPlayerDuplication(name)) {
            Player tmp = new Player(name);
            addPlayer(tmp);
        } else
            throw new DuplicateException("Playername nicht doppelt verwendbar!");
    }

    /**
     * extendPlayerArray()
     *      vergroesert die playerArray um 10 Plaetze.
     */
    private void extendPlayerArray() {
        Player[] newArray = new Player[playerArray.length + 10];

        for (int c = 0; c < playerArray.length; c++)
            newArray[c] = playerArray[c];

        playerArray = newArray;
    }

    /**
     * addPlayer(Player tmp) 
     *      adds Player to the playerArray, if to short, extends Array;
     */
    private void addPlayer(Player tmp) {
        int foundPos = 0;
        while (playerArray[foundPos] != null && foundPos < playerArray.length)
            foundPos++;

        if (foundPos == playerArray.length) {
            extendPlayerArray();
            addPlayer(tmp);
        } else
            playerArray[foundPos] = tmp;
    }
  
    /**
     * findPlayer(String s)
     */
    private Player findPlayer(String s) {
        int foundPos = 0;
        while (playerArray[foundPos] != null && foundPos < playerArray.length
                && !playerArray[foundPos].getName().equals(s))
            foundPos++;
        if (foundPos == playerArray.length || playerArray[foundPos] == null)
            throw new PlayerNotFoundException("Player nicht gefunden!");
        else
            return playerArray[foundPos];
    }

    /**
     * checkPlayerDouplication(String playerName)
     *      sucht in der playerArray nach Spielern mit dem gleichen Namen
     */
    private boolean checkPlayerDuplication(String playerName) {
        boolean duplication = false;

        for (int c = 0; c < playerArray.length; c++) {
            if (playerArray[c] != null && playerArray[c].getName().equals(playerName)) {
                duplication = true;
                break;
            }
        }
        return duplication;
    }
 
    /**
     * getAssetValue(String playerName)
     *		gibt den AKTUELLEN Wert von CashAccount und Deposit eines Spielers zurueck
     */
    public long getAssetValue(String playerName) {
        Player foundPlayer = findPlayer(playerName);
        long value = foundPlayer.getDeposit().getCurrentValue()
                + foundPlayer.getAccount().getAccountStatus();
        return value;
    }
    
    /**
     * getDepositValue(String playerName)
     */
    public long getDepositValue(String playerName){
        Player foundPlayer = findPlayer(playerName);
        return foundPlayer.getDeposit().getCurrentValue();
    }
    
    /**
     * getCashAccountValue(String playerName)
     */
    public long getCashAccountValue(String playerName) {
        Player foundPlayer = findPlayer(playerName);
        return foundPlayer.getAccount().getAccountStatus();
    } 

    /**
     * toString()
     */
    public String toString() {
        String s = "";
        s = "All registered Players: \r";

        for (int c = 0; playerArray[c] != null && c < playerArray.length; c++)
            s = s + playerArray[c].toString() + '\r';

        return s + spp.toString();
    }

    /**
     * setCashAccountStatus (String playerName, long amount)
     */
    public void setCashAccountStatus(String playerName, long amount) {
        try {
            Player foundPlayer = findPlayer(playerName);

            foundPlayer.getAccount().setAccountStatus(amount);
        } catch (PlayerNotFoundException i) {
            i.printStackTrace();
            return;
        }
    }
}
