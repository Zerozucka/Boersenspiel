import animalpackage.Share;
import java.util.Random;

public class RandomStockPriceProvider extends StockPriceProvider {
    public RandomStockPriceProvider() {

    }

    public void updateShareRate(Share share) {
    	Share foundShare = super.findShare(share.getName());
    }
    
    public void updateShareRates(){
    	
    }
}
