package view;

import domain.BlackjackResultDTO;
import domain.Card;
import domain.Player;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
            System.out.print(card.getDenomination().getValue(0) + "" + card.getSymbol() + ", ");
        }
        System.out.println();
    }

    public static void printResults(List<Player> players) {
        for (Player player : players) {
            printPlayerStatus(player);
            System.out.println(" - 결과 : " + player.calculateScore());
        }
    }

    public static void printGameResults(BlackjackResultDTO blackjackResult) {
        System.out.println("## 최종 승패");
        for (var player : blackjackResult.results().keySet()) {
            Integer win = blackjackResult.getWin(player);
            Integer lose = blackjackResult.getLose(player);
            printWinOrLose(player, win, lose);
        }
    }

    private static void printWinOrLose(final Player player, final Integer win, final Integer lose) {
        if (player.getName().equals("딜러")) {
            System.out.print(player.getName() + ": " + win + "승" + lose + "패\n");
            return;
        }
        System.out.println(player.getName() + ": " + (win > 0 ? "승" : "패"));

    }
}
