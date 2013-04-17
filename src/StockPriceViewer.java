
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class StockPriceViewer extends JFrame {
    private GlobalTimer timer = GlobalTimer.getTimer();
    private JTextArea shareArea;
    private StockPriceProvider spp;
    
    public StockPriceViewer(AccountManager acc){
    	this.spp = acc.getStockPriceProvider();
    	timer.setStockPriceProvider(spp);
    	timer.setStockPriceViewer(this);
    	init();
    }

    public void init() {
        shareArea = new JTextArea("initialize...");
        shareArea.setEditable(false);
        add("Center", shareArea);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(250, 500);
        setVisible(true);
    }
    
    public void start() {
        shareArea.setText(spp.allShares());
    }
}