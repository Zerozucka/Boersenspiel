import exceptionpackage.DuplicateException;
import exceptionpackage.NotEnoughMoneyException;
import animalpackage.Player;


public class CommandProcessor {

	AccountManager acc;
	String currentPlayer;
	
	public CommandProcessor(AccountManager acc){
		this.acc = acc;
	}
	
	public void startProcessor() throws Exception{
		
		while(true){
			
			if(currentPlayer == null){
				System.out.println("Please enter your player name");
				String playerName = System.console().readLine();
				
				try{
					acc.createPlayer(playerName);
					System.out.println("Spieler konnte nicht gefunden werden, ein neuer Spieler wurde erstellt");
				} catch(DuplicateException e){
					System.out.println("Spieler gefunden, login erfolgt");
				}
				currentPlayer = playerName;
			}
			
			String s = System.console().readLine();
			
			switch(s){
			
			case("buyShare"):{
				System.out.println("Name der zu kaufenden Aktien eingeben");
				String shareName = System.console().readLine();
				
				System.out.println("Menge Eingeben");
				String amountInput = System.console().readLine();
				long amount = Integer.parseInt(amountInput);
				
				try{
				acc.buyShare(currentPlayer, shareName, amount);
				} catch(NotEnoughMoneyException e){
					System.out.println("Nicht ausreichend Geld vorhanden");
				}
				System.out.println("Kauf der Aktie" + shareName + " erfolgreich");
			break;
			}
			
			case("sellShare"):{
				System.out.println("Name der zu verkaufenden Aktien eingeben");
				String shareName = System.console().readLine();
			
				System.out.println("Menge Eingeben");
				String amountInput = System.console().readLine();	
				long amount = Integer.parseInt(amountInput);
			
			
				acc.sellShare(currentPlayer, shareName, amount);
				
				System.out.println("Verkauf der Aktie" + shareName + " erfolgreich");
			break;
			}
			
			case("CashAccountValue"):
				System.out.println("Aktuelles Guthaben: " + acc.getCashAccountValue(currentPlayer));
				break;
			
			case("DepositValue"):
				System.out.println("Aktuelles Wert des Depots: " + acc.getDepositValue(currentPlayer));
				break;
			
			case ("logout"):
				currentPlayer = null;
				System.out.println("Logout erfolgreich");
				break;
				
			case ("AssetValue"):
				System.out.println("Aktueller Wert aller Vermögenswerte " + acc.getAssetValue(currentPlayer));
				break;
			
			case ("quit"):
				System.out.println("Goodbye");
				return;
				
			case("help"):{
				String commands = 
						"buyShare             --- Kauft Aktien \n" +
						"sellShare            --- Verkauft Aktien \n" +
						"CashAccountValue     --- Zeigt das aktuelle Guthaben an \n" +
						"DepositValue         --- Zeigt den aktuellen Wert aller gekauften Aktien an \n" +
						"AssetValue           --- Zeigt das gesamte Vermögen an \n" +
						"logout               --- Meldet den derzeit angemeldeten Nutzer ab \n" +
						"quit                 --- Beendet das Programm";
				
				System.out.println(commands);
				break;
			}
			
			default:
				System.out.println("Der Befehl " + s + "ist falsch geschrieben oder konnte nicht erkannt werden. \n Geben sie <help> für eine Liste aller Befehle ein. \n Geben sie <quit> zum beenden ein.");
				break;
			
			}
		}
	}
}
