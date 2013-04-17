
public class CommandProcessorTest {
	   private final static int RANDOM = 0;
	   private final static int CONST = 1;
	
	public static void main(String[] args) throws Exception{
		 AccountManager acc = new AccountManagerImpl();
	     acc.setStockPriceProvider(RANDOM);
	     StockPriceInfo spp = acc.getStockPriceProvider();
	     StockPriceViewer spv = new StockPriceViewer(acc);
	     CommandProcessor cmd = new CommandProcessor(acc);
	     
	     spp.createShare("BMW", 5000);
         spp.createShare("Google", 70000);
         spp.createShare("VW", 10000);
         
         spv.start();
         
         cmd.startProcessor();

	}
}
