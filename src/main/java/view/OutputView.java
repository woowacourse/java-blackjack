package view;

import domain.Card;
import domain.Gambler;
import domain.Player;
import domain.Players;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NAME_FORMAT = "카드: ";
    private static final String INIT = "";

    private OutputView() {
    }

    public static void printPlayersCards(Players players) {
        for (Player player : players.getPlayers()) {
            printPlayerName(player);
            printPlayerCards(player);
        }
    }

    public static void printPlayerName(Gambler gambler) {
        String name = gambler.getName();
        System.out.print(name + NAME_FORMAT);
    }

    public static void printPlayerCards(Gambler gambler) {
        String output = getPlayerCards(gambler);
        System.out.println(String.join(DELIMITER, output));
    }

    public static String getPlayerCards(Gambler gambler) {
        String output = INIT;
        for (Card card : gambler.getCards()) {
            output += card.getSuit() + card.getName();
        }
        return output;
    }
}