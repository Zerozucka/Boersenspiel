import animalpackage.Player;
import animalpackage.Share;
import assetpackage.ShareItem;
import exceptionpackage.DuplicateException;
import exceptionpackage.ShareNotFoundException;

public abstract class StockPriceProvider implements StockPriceInfo {
    protected Share[] shareArray = new Share[100];
    protected GlobalTimer timer = GlobalTimer.getTimer();
    
    /**
     * getShareArray()
     */
    public Share[] getShareArray(){
    	return shareArray;
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
     * updateShareRate(Share share)
     */
    protected abstract void updateShareRate(Share share);
    
    
    /**
     * updateShareRates()
     */
    protected void updateShareRates() {
        
    }
    
    /**
     * startUpdate()
     */
    public void startUpdate() {
    	timer.setStockPriceProvider(this);
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
    public Share findShare(String s) {
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
     * getShareValue(String playerName, String share)
     */
    public long getShareValue(Player player, String share) {
        ShareItem[] itemArray = player.getDeposit().getItemArray();
        
        int foundPos1 = 0;
        while (itemArray[foundPos1] != null && !itemArray[foundPos1].getName().equals(share) && foundPos1 < itemArray.length)
            foundPos1++;
    
        return itemArray[foundPos1].getCurrentValue();
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
        for (int c = 0; shareArray[c] != null && c < shareArray.length; c++)
            s = s + shareArray[c].toString() + '\n';
        return s;
    }

    /**
     * toString()
     */
    public String toString() {
        String s = "";
        
        s = s + '\r' + "All registered Shares:" + '\r';

        for (int c = 0; shareArray[c] != null && c < shareArray.length; c++)
            s = s + shareArray[c].toString() + '\r';

        return s;
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
}
