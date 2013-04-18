import exceptionpackage.NotEnoughMoneyException;
import exceptionpackage.PlayerNotFoundException;

public class CommandProcessor {
    AccountManager acc;
    String currentPlayer;

    public CommandProcessor(AccountManager acc) {
        this.acc = acc;
    }

    public void startProcessor() throws Exception {
        while (true) {
            if (currentPlayer == null) {
                System.out.println("Please enter your player name");
                String playerName = System.console().readLine("> ");

                if (playerName.equals("quit")) {
                    System.out.println("Goodbye");
                    System.exit(0);
                }


                try {
                    acc.findPlayer(playerName);
                    System.out.println("Spieler gefunden, Login erfolgt!\n");
                } catch (PlayerNotFoundException e) {
                    System.out.println("Spieler konnte nicht gefunden werden, soll ein neuer Spieler erstellt werden? (ja/nein)");
                    String abfrage = System.console().readLine("> ");
                    switch (abfrage) {
                        case "ja":
                            acc.createPlayer(playerName);
                            System.out.println("Spieler wurde erstellt!\n");
                            break;
                        case "nein":
                            System.out.println("Es wurde kein Spieler erstellt!\n");
                            playerName = null;
                            break;
                        default:
                            playerName = null;
                            break;
                    }
                }
                currentPlayer = playerName;
            } else {

                String s = System.console().readLine("> ");

                switch (s) {
                    case ("buy"): {
                        System.out.println("Name der zu kaufenden Aktien eingeben:");
                        String shareName = System.console().readLine("> ");

                        System.out.println("Wie viele Aktien sollen gekauft werden?");
                        String amountInput = System.console().readLine("> ");

                        try {
                            long amount = Integer.parseInt(amountInput);
                            acc.buyShare(currentPlayer, shareName, amount);
                            System.out.println("Kauf der Aktie " + shareName + " erfolgreich\n");
                        } catch (NotEnoughMoneyException e) {
                            System.out.println("Nicht ausreichend Geld vorhanden!\n");
                        } catch (NumberFormatException e) {
                            System.out.println("Keine Zahl eingegeben!\n");
                        }
                        break;
                    }

                    case ("sell"): {
                        System.out.println("Name der zu verkaufenden Aktien eingeben");
                        String shareName = System.console().readLine("> ");

                        System.out.println("Menge Eingeben");
                        String amountInput = System.console().readLine("> ");

                        try {
                            long amount = Integer.parseInt(amountInput);
                            acc.sellShare(currentPlayer, shareName, amount);
                            System.out.println("Verkauf der Aktie " + shareName + " erfolgreich");
                        } catch (NumberFormatException e) {
                            System.out.println("Keine Zahl eingegeben!\n");
                        }

                        break;
                    }

                    case ("cash"):
                        System.out.println("Aktuelles Guthaben: " + acc.getCashAccountValue(currentPlayer) + " Cent\n");
                        break;

                    case ("depovalue"):
                        System.out.println("Aktuelles Wert des Depots: " + acc.getDepositValue(currentPlayer)
                                + " Cent\n");
                        break;

                    case ("logout"):
                        currentPlayer = null;
                        System.out.println("Logout erfolgreich\n");
                        break;

                    case ("assetvalue"):
                        System.out.println("Aktueller Wert aller Vermoegenswerte " + acc.getAssetValue(currentPlayer)
                                + " Cent\n");
                        break;

                    case ("quit"):
                        System.out.println("Goodbye");
                        System.exit(0);
                        break;
                    case ("help"): {
                        String commands = "buy                  --- Kauft Aktien\n"
                                + "sell                 --- Verkauft Aktien\n"
                                + "cash                 --- Zeigt das aktuelle Guthaben an\n"
                                + "depovalue            --- Zeigt den aktuellen Wert aller gekauften Aktien an\n"
                                + "assetvalue           --- Zeigt das gesamte Vermoegen an\n"
                                + "logout               --- Meldet den derzeit angemeldeten Nutzer ab\n"
                                + "quit                 --- Beendet das Programm\n";

                        System.out.println(commands);
                        break;
                    }

                    default:
                        System.out
                                .println("Der Befehl "
                                        + s
                                        + " ist falsch geschrieben oder konnte nicht erkannt werden.\nGeben Sie <help> fuer eine Liste aller Befehle ein.\nGeben Sie <quit> zum Beenden ein.\n");
                        break;
                }
            }
        }
    }
}
