import javax.swing.JFrame;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class StockPriceViewer extends JFrame {
    private GlobalTimer timer = GlobalTimer.getTimer();
    private JTextArea shareArea;
    private StockPriceInfo spi;
    
    public StockPriceViewer(AccountManager acc){
    	this.spi = acc.getStockPriceProvider();
    	spi.startUpdate();
    	timer.setStockPriceViewer(this);
    	init();
    }

    public void init() {
        shareArea = new JTextArea("> initialize...");
        shareArea.setEditable(false);
        add("Center", shareArea);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(250, 500);
        setVisible(true);
    }
    
    public void start() {
        shareArea.setText(spi.allShares());
    }
}