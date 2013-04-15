import exceptionpackage.DuplicateException;
import exceptionpackage.NotEnoughMoneyException;

	//testchange

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
}
