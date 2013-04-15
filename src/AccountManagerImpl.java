import exceptionpackage.DuplicateException;
import exceptionpackage.NegativeAmountException;
import exceptionpackage.NotEnoughMoneyException;
import exceptionpackage.PlayerNotFoundException;
import exceptionpackage.ShareNotFoundException;
//test
public class AccountManagerImpl implements AccountManager {

    private Player[] playerArray = new Player[10];
    private Share[] shareArray = new Share[100];

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
     * buyShare(String playerName, String shareName, long amount)
     * @throws NotEnoughMoneyException 
     */
    public void buyShare(String playerName, String shareName, long amount) throws NotEnoughMoneyException {
        Share foundShare = findShare(shareName);
        Player foundPlayer = findPlayer(playerName);
        
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
        Share foundShare = findShare(shareName);
        Player foundPlayer = findPlayer(playerName);
        
        if (amount < 0)
            throw new NegativeAmountException("Negativer Amount nicht möglich");
        System.out.println(foundPlayer.getName() + " will eine Aktie von " + foundShare.getName() + " verkaufen!");

        long cost = foundShare.getRate() * amount;
        foundPlayer.getAccount().addMoney(cost);
        foundPlayer.getDeposit().deleteShare(foundShare, amount);
    }

    /**
     * getShareRate(String shareName)
     *      gibt den aktuellen Kurs eine Share zurueck. Suche anhand von Name (String).
     */
    public long getShareRate(String shareName) {
        Share foundShare = findShare(shareName);
        long tmp = foundShare.getRate();
        return tmp;
    }

    /**
     * allShares() returns shareArray.toString()
     *      String mit allen Shares, Name und Wert
     */
    public String allShares() {
        String s = "";
        for (int c = 0; shareArray[c] != null && c < playerArray.length; c++)
            s = s + shareArray[c].toString() + '\r';
        return s;
    }

    /**
     * createShare(String name, long rate)
     *      erzeugt eine neue Share und fuegt sie an die ShareArray an. 
     *      Ueberprueft vor dem Anfuegen, ob die Share schon vorhanden ist.
     */
    public void createShare(String name, long rate) throws DuplicateException {
        if (checkShareDuplication(name)) {
            throw new DuplicateException("Share schon vorhanden!");
        } else {
            Share tmp = new Share(name, rate);
            addShare(tmp);
        }
    }
    
    /**
     * addShare (Share tmp)
     */
    private void addShare(Share tmp) {
        int foundPos = 0;
        while (foundPos < shareArray.length && shareArray[foundPos] != null)
            foundPos++;
        shareArray[foundPos] = tmp;
    }

    /**
     * findShare(String s)
     */
    private Share findShare(String s) {
        int foundPos = 0;
        while (shareArray[foundPos] != null && foundPos < shareArray.length
                && !shareArray[foundPos].getName().equals(s))
            foundPos++;
        if (foundPos == shareArray.length || shareArray[foundPos] == null)
            throw new ShareNotFoundException("Share nicht gefunden!");
        else
            return shareArray[foundPos];
    }

    /**
     * checkShareDouplication(String playerName) 
     *      sucht in der shareArray nach Shares mit dem gleichen Namen
     */
    private boolean checkShareDuplication(String shareName) {
        boolean duplication = false;

        for (int c = 0; c < shareArray.length; c++) {
            if (shareArray[c] != null && shareArray[c].getName().equals(shareName)) {
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
     * toString()
     */
    public String toString() {
        String s = "";
        s = "All registered Players: \r";

        for (int c = 0; playerArray[c] != null && c < playerArray.length; c++)
            s = s + playerArray[c].toString() + '\r';

        s = s + '\r' + "All registered Shares:" + '\r';

        for (int c = 0; shareArray[c] != null && c < playerArray.length; c++)
            s = s + shareArray[c].toString() + '\r';

        return s;
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
