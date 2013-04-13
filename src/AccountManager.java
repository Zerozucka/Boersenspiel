import exceptionpackage.DuplicateException;

/*test
 * test
 */

public interface AccountManager{
	
	public void createPlayer(String name) throws DuplicateException;
	
	public void buyShare(String playerName, String shareName, long amount);
	
	public void sellShare(String playerName, String shareName, long amount);
	
	public long getAllAssetsValue(String playerName);
	
	/*
	 * getAssetValue(String playerName, String type);
	 * 		gibt den wert eines bestimmten Assets zurueck
	 * 			types:
	 * 			-cashAccount
	 * 			-deposit
	 * 			-fuer shareItem Aktienname �bergeben
	 */
	public long getAssetValue(String playerName, String type);
		
	public long getShareRate(String shareName);
	
	public String allShares();
	
	/*
	 * createShare(String name, long rate);
	 * 		creates a share and adds it to a list of all available shares
	 */
	public void createShare(String name, long rate) throws DuplicateException;
}
