import exceptionpackage.DuplicateException;
import exceptionpackage.NotEnoughMoneyException;

public interface AccountManager {
    /**
     * createPlayer(String name)
     */
    public void createPlayer(String name) throws DuplicateException;

    /**
     * buyShare(String playerName, String shareName, long amount)
     * @throws NotEnoughMoneyException 
     */
    public void buyShare(String playerName, String shareName, long amount) throws NotEnoughMoneyException;

    /**
     * sellShare(String playerName, String shareName, long amount)
     */
    public void sellShare(String playerName, String shareName, long amount);

    /**
     * getAssetValue(String playerName, String type) 
     *      gibt den wert eines bestimmten Assets zurueck types: 
     *          - cashAccount 
     *          - deposit
     *          - fuer shareItem Aktienname uebergeben
     */
    public long getAssetValue(String playerName);

    /**
     * getShareRate(String shareName)
     */
    public long getShareRate(String shareName);

    /**
     * allShares()
     */
    public String allShares();

    /**
     * createShare(String name, long rate)
     * creates a share and adds it to a list of all available shares
     */
    public void createShare(String name, long rate) throws DuplicateException;
}
