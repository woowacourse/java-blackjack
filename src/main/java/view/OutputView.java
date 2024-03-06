package view;

import domain.Card;
import domain.Player;
import java.util.List;

public class OutputView {
    public static void printPlayersStatus(List<Player> players) {
        for (Player player : players) {
            printPlayerStatus(player);
            System.out.println();
        }
    }

    public static void printPlayerStatus(final Player player) {
        System.out.print(player.getName() + " : ");
        for (Card card : player.getCards()) {
            System.out.print(card.getSymbol() + card.getDenomination().toString());
        }
        System.out.println();
    }

    public static void printDealerNotification(int score) {
        System.out.println("딜러는 " + score + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResults(List<Player> players) {
        for (Player player : players) {
            printPlayerStatus(player);
            System.out.println(" - 결과 : " + player.calculateScore());
        }
    }
}
