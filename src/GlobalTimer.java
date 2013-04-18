import java.util.Timer;
import java.util.TimerTask;

/**
 * GlobalTimer singelton class Es kann nur einen GlobalTimer geben, mehrere Instanzen koennen nicht erzeugt werden Timer kann nur ueber die statisches Methode getTimer instanziiert / aufgerufen werden
 */
class GlobalTimer {
    private static GlobalTimer timer;

    /**
     * GlobalTimer() constructor ist private fuer Singleton implementierung
     */
    private GlobalTimer() {
        startTiming();
    }

    /**
     * getTimer() instanziiert beim ersten aufruf den Timer, bei weiteren aufrufen wird der bereits instanziierte timer zurueckgegeben
     */
    public static GlobalTimer getTimer() {
        if (timer == null)
            timer = new GlobalTimer();

        return timer;
    }

    private StockPriceInfo spi = null;

    /**
     * setStockPriceInfo(StockPriceInfo spi)
     */
    public void setStockPriceInfo(StockPriceInfo spi) {
        this.spi = spi;
    }

    private StockPriceViewer spv = null;

    /**
     * setStockPriceViewer(StockPriceViewer spv)
     */
    public void setStockPriceViewer(StockPriceViewer spv) {
        this.spv = spv;
    }

    /**
     * modifyUserObject()
     */
    private void modifyUserObject() {
        if (spi != null)
            spi.updateShareRates();
        try {
            spv.update();
        } catch (NullPointerException e) {
            System.out.println("> catched");
        }
    }

    /**
     * startTiming()
     */
    public void startTiming() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                GlobalTimer.this.modifyUserObject();
            }
        }, 2000, 1000);
    }
}
