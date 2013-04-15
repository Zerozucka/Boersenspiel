import animalpackage.Player;
import exceptionpackage.DuplicateException;

public interface StockPriceInfo {
    
    /**
     * getShareValue(String playerName, String share)
     */
    public long getShareValue(Player player, String share);
    
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