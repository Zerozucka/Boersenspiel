import animalpackage.Share;
import java.util.Random;

public class RandomStockPriceProvider extends StockPriceProvider {
    private int changeRate = 20;
	Random randomGenerator = new java.util.Random();
	
	/**
	 * RandomStockPriceProvider()
	 */
    public RandomStockPriceProvider() {

    }

    /**
     * updateShareRate(Share share)
     * 		sucht in der shareArray nach der uebergebenen Share
     * 		dem aktuellen Kurs der Aktien wird ein Zufallswert < 50 je nach Zufall hinzugefuegt, oder abgezogen
     * 		Der Wert einer Aktie kann nicht unter 0 sinken, sollte das eintreten wird der naechste Zufallswert
     * 		automatisch addiert.
     */
    protected void updateShareRate(Share share) {
    	Share foundShare = super.findShare(share.getName());
    	
    		if(randomGenerator.nextBoolean() && foundShare.getRate() >= 0)
    			foundShare.setRate(foundShare.getRate() -  randomGenerator.nextInt(changeRate));	
    		else
    			foundShare.setRate(foundShare.getRate() +  randomGenerator.nextInt(changeRate));	
    	
    }
    
    /**
     * updateShareRates()
     * 		dem aktuellen Kurs der Aktien wird ein Zufallswert < 50 je nach Zufall hinzugefuegt, oder abgezogen
     * 		Der Wert einer Aktie kann nicht unter 0 sinken, sollte das eintreten wird der naechste Zufallswert
     * 		automatisch addiert.
     */
    public void updateShareRates() {
    	Share[] shareArray = super.getShareArray();
    	
    	for(int c = 0; shareArray[c] != null && c < shareArray.length; c++){
    		if(randomGenerator.nextBoolean() && shareArray[c].getRate() >= changeRate)
    			shareArray[c].setRate(shareArray[c].getRate() - randomGenerator.nextInt(changeRate));	
    		else
    			shareArray[c].setRate(shareArray[c].getRate() + randomGenerator.nextInt(changeRate));	
    	}
    }
}
