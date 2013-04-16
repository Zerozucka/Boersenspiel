import animalpackage.Share;

public class ConstStockPriceProvider extends StockPriceProvider {
	
    public ConstStockPriceProvider() {
    	
    }
    /**
     * startUpdate()
     * 		wird hier "implementiert", da bei Konstaten Raten keine Update Funktion nötig ist.
     */
    public void startUpdate() {
        /*soll leer bleiben*/
    }
    
	public void updateShareRate(Share share) {
	    
	}


	public void updateShareRates() {

		
	}
}
