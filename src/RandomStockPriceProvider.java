import animalpackage.Share;
import java.util.Random;

public class RandomStockPriceProvider extends StockPriceProvider {
    
	Random randomGenerator = new java.util.Random();
	
    public RandomStockPriceProvider() {

    }

    /**
     * updateShareRate(Share share)
     * 		sucht in der shareArray nach der übergebenen Share
     * 		ersetzt den Aktuellen Kurs einer übergebenen Share durch einen Zufallswert.
     */
    public void updateShareRate(Share share) {
    	Share foundShare = super.findShare(share.getName());
    	foundShare.setRate(randomGenerator.nextInt(1000));
    }
    
    /**
     * updateShareRates()
     * 		ersetzt alle Share Werte in der shareArray durch Zufallszahlen < 1000.
     */
    public void updateShareRates() {
    	Share[] shareArray = super.getShareArray();
    	for(int c = 0; c < shareArray.length; c++)
    		shareArray[c].setRate(randomGenerator.nextInt(1000));	
    }
}
