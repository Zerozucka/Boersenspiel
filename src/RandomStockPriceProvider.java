import animalpackage.Share;
import java.util.Random;

public class RandomStockPriceProvider extends StockPriceProvider {
    
	Random randomGenerator = new java.util.Random();
	
	/**
	 * RandomStockPriceProvider()
	 */
    public RandomStockPriceProvider() {

    }

    /**
     * updateShareRate(Share share)
     * 		sucht in der shareArray nach der uebergebenen Share
     * 		dem aktuellen Kurs der Aktien wird ein Zufallswert < 50 je nach Zufall hinzugef�gt, oder abgezogen
     * 		Der Wert einer Aktie kann nicht unter 0 sinken, sollte das eintreten wird der n�chste Zufallswert
     * 		automatisch addiert.
     */
    protected void updateShareRate(Share share) {
    	Share foundShare = super.findShare(share.getName());
    	
    		if(randomGenerator.nextBoolean() && foundShare.getRate() >= 0)
    			foundShare.setRate(foundShare.getRate() -  randomGenerator.nextInt(50));	
    		else
    			foundShare.setRate(foundShare.getRate() +  randomGenerator.nextInt(50));	
    	
    }
    
    /**
     * updateShareRates()
     * 		dem aktuellen Kurs der Aktien wird ein Zufallswert < 50 je nach Zufall hinzugef�gt, oder abgezogen
     * 		Der Wert einer Aktie kann nicht unter 0 sinken, sollte das eintreten wird der n�chste Zufallswert
     * 		automatisch addiert.
     */
    public void updateShareRates() {
    	Share[] shareArray = super.getShareArray();
    	
    	for(int c = 0; shareArray[c] != null && c < shareArray.length; c++){
    		if(randomGenerator.nextBoolean() && shareArray[c].getRate() >= 50)
    			shareArray[c].setRate(shareArray[c].getRate() - randomGenerator.nextInt(50));	
    		else
    			shareArray[c].setRate(shareArray[c].getRate() + randomGenerator.nextInt(50));	
    	}
    }
}
