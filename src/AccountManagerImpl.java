import exceptionpackage.DuplicateException;
import exceptionpackage.NotEnoughMoneyException;
import exceptionpackage.NotFoundException;

public class AccountManagerImpl implements AccountManager {

    private Player[] playerArray = new Player[10];
    private Share[] shareArray = new Share[100];

    /*
     * createPlayer(String name)) 
     * ueberprueft, ob schon ein Spieler mit dem Namen existiert, wenn das der Fall ist wird eine DuplicationException geworfen
     */
    public void createPlayer(String name) throws DuplicateException {
        if (!checkPlayerDuplication(name)) {
            Player tmp = new Player(name);
            addPlayer(tmp);
        } else
            throw new DuplicateException("Playername nicht doppelt verwendbar!");
    }

    /*
     * buyShare(String playerName, String shareName, long amaunt)
     */
    public void buyShare(String playerName, String shareName, long amount) {
        try {
            int foundSharePos = findShare(shareName);
            int foundPlayerPos = findPlayer(playerName);

            playerArray[foundPlayerPos].buy(shareArray[foundSharePos], amount);
        } catch (NotEnoughMoneyException i) {
            i.printStackTrace();
            // System.out.println("Share or player not found");
            return;
        }
    }
    
    /*
     *sellShare(String playerName, String shareName, long amount) 
     */
    public void sellShare(String playerName, String shareName, long amount) {
        int foundSharePos = findShare(shareName);
        int foundPlayerPos = findPlayer(playerName);
        
        playerArray[foundPlayerPos].sell(shareArray[foundSharePos], amount);
    }
 
    /*
     *getAllAssetsValue(String playerName)
     *		gibt den AKTUELLEN Wert von CashAccount und Deposit eines Spielers zurueck
     */
    public long getAllAssetsValue(String playerName) {
        int foundPos = findPlayer(playerName);
        long value = playerArray[foundPos].getDeposit().getCurrentValue()
                + playerArray[foundPos].getAccount().getAccountStatus();
        return value;
    }
    
    /**
     *getAssetValue(String playerName, String type)
     *		gibt ein Bestimmtes Asset eines Spielers zurueck. Auswahl durch den Type String:
     *			-cashAccount
	 * 			-deposit
	 * 			-fuer shareItem Aktienname uebergeben
	 * 
	 * Wenn ein falscher Type uebergeben wird, wird eine NotFoundException geworfen
     */
    public long getAssetValue(String playerName, String type){
        int foundPos = findPlayer(playerName);
        if(type.equals("cashAccount")){
        	return playerArray[foundPos].getAccount().getAccountStatus();
        }
        else if(type.equals("deposit")){
        	return playerArray[foundPos].getDeposit().getCurrentValue();
        }
        else if(checkShareDuplication(type)){
        	ShareItem[] itemArray = playerArray[foundPos].getDeposit().getItemArray();
        	
        	 int foundPos1 = 0;
             while (itemArray[foundPos1] != null && !itemArray[foundPos1].getName().equals(type) && foundPos1 < itemArray.length)
                 foundPos1++;
             
             return itemArray[foundPos1].getCurrentValue();
        }
        else{
        	throw new NotFoundException("Overhand type couldn't be handled");
        }
    }
    /*
     * getShareRate(String shareName)
     * 		gibt den aktuellen Kurs eine Share zurueck. Suche anhand von Name (String).
     */
    public long getShareRate(String shareName) {
        int foundPos = findShare(shareName);
        long tmp = shareArray[foundPos].getRate();
        return tmp;
    }

    /*
     * allShares() returns shareArray.toString();
     * 		String mit allen Shares, Name und Wert
     */
    public String allShares() {
        String s = "";
        for (int c = 0; shareArray[c] != null && c < playerArray.length; c++)
            s = s + shareArray[c].toString() + '\r';
        return s;
    }

    /*
     * createShare(String name, long rate)
     * 		erzeugt eine neue Share und fuegt sie an die ShareArray an. Ueberprueft vor dem Anfuegen, ob
     * 		die Share schon verfuegbar ist.
     */
    public void createShare(String name, long rate) throws DuplicateException {
        if (checkShareDuplication(name)) {
            throw new DuplicateException("Share schon vorhanden!");
        } else {
            Share tmp = new Share(name, rate);
            addShare(tmp);
        }
    }

    /*
     * playerArray methods
     * 		vergroesert die playerArray um 10 Plaetze.
     */
    private void extendPlayerArray() {
        Player[] newArray = new Player[playerArray.length + 10];

        for (int c = 0; c < playerArray.length; c++)
            newArray[c] = playerArray[c];

        playerArray = newArray;
    }

    /*
     * addPlayer(Player tmp) 
     * 		adds Player to the playerArray, if to short, extends Array;
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
  
    private int findPlayer(String s) {
        int foundPos = 0;
        while (playerArray[foundPos] != null && foundPos < playerArray.length
                && !playerArray[foundPos].getName().equals(s))
            foundPos++;
        if (foundPos == playerArray.length || playerArray[foundPos] == null)
            throw new NotFoundException("Player nicht gefunden!");
        else
            return foundPos;
    }

    /*
     * checkPlayerDouplication(String playerName) �
     * 		sucht in der playerArray nach Spielern mit dem gleichen Namen
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

    /*
     * ShareArray operations
     */

    private void addShare(Share tmp) {
        int foundPos = 0;
        while (foundPos < shareArray.length && shareArray[foundPos] != null)
            foundPos++;
        shareArray[foundPos] = tmp;
    }

    private int findShare(String s) {
        int foundPos = 0;
        while (shareArray[foundPos] != null && foundPos < shareArray.length
                && !shareArray[foundPos].getName().equals(s))
            foundPos++;
        if (foundPos == shareArray.length || shareArray[foundPos] == null)
            throw new NotFoundException("Share nicht gefunden!");
        else
            return foundPos;
    }

    /*
     * checkShareDouplication(String playerName) sucht in der shareArray nach Shares mit dem gleichen Namen
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

    /*
     * temporär
     */
    public void setCashAccountStatus(String playerName, long amount) {
        try {
            int tmp = findPlayer(playerName);

            playerArray[tmp].getAccount().setAccountStatus(amount);
        } catch (NotFoundException i) {
            i.printStackTrace();
        }
    }
}
