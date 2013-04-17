import exceptionpackage.DuplicateException;
import exceptionpackage.NotEnoughMoneyException;

public interface AccountManager {
    /**
     * createPlayer(String name)
     * 		jeder Spieler startet mit einem Kapital von 1000 Euro
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
     */
    public long getAssetValue(String playerName);

    /**
     * getDepositValue(String playerName)
     */
    public long getDepositValue(String playerName);
    
    /**
     * getCashAccountValue(String playerName)
     */
    public long getCashAccountValue(String playerName);

    public StockPriceProvider getStockPriceProvider();

    public void setCashAccountStatus(String string, long l);

    public void setStockPriceProvider(int random);
}
