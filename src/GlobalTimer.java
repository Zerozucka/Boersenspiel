import java.util.Timer;
import java.util.TimerTask;

/**
 * GlobalTimer
 * 		singelton class
 * 		Es kann nur einen GlobalTimer geben, mehrere Instanzen koennen nicht erzeugt werden
 * 		Timer kann nur ueber die statisches Methode getTimer instanziiert / aufgerufen werden
 */
class GlobalTimer  {
	private static GlobalTimer timer;
	private StockPriceProvider spp;		//bad implementation
	
	public void setStockPriceProvider(StockPriceProvider tmp){
		spp = tmp;
	}
	
	/**
	 * GlobalTimer()
	 * 		constructor ist private fuer Singleton implementierung
	 */
	private GlobalTimer(){}
	
	
	/**
	 * getTimer()
	 * 		instanziiert beim ersten aufruf den Timer, bei weiteren aufrufen wird der bereits instanziierte timer zurueckgegeben
	 */
	public static GlobalTimer getTimer(){
		if(timer == null)
			timer = new GlobalTimer();
		
		return timer;	
	}
    
	
	private StockPriceInfo spi = null;
	public void setStockPriceInfo(StockPriceInfo spi) {
	    this.spi = spi;
	}
    
    private void modifyUserObject() {
    	spp.updateShareRates();
    }
    
    public void startTiming() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                GlobalTimer.this.modifyUserObject();
            }
        }, 2000, 1000);
    }
}
