

import animalpackage.Share;

public class ConstStockPriceProvider extends StockPriceProvider {
	
    public ConstStockPriceProvider() {
    	
    }
    /**
     * startUpdate()
     * 		wird hier "implementiert", da bei Konstaten Raten keine Update Funktion noetig ist.
     */
    public void startUpdate() {}
    
    /**
     * updateShareRate(Share share)
     */
	public void updateShareRate(Share share) {}
}
