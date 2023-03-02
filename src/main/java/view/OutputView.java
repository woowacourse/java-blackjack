package view;

import domain.Card;
import domain.Gambler;
import domain.Player;
import domain.Players;

public class OutputView {

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
        System.out.print(name + "카드: ");
    }

    public static void printPlayerCards(Gambler gambler) {
        String output = getPlayerCards(gambler);
        System.out.println(String.join(", ", output));
    }

    public static String getPlayerCards(Gambler gambler) {
        String output = "";
        for (Card card : gambler.getCards()) {
            output += card.getSuit() + card.getName();
        }
        return output;
    }
}