package view;

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

    public static void printGameResults(final Map<Player, Entry<Integer, Integer>> playerIntegerMap) {
        for (final Entry<Player, Entry<Integer, Integer>> playerEntryEntry : playerIntegerMap.entrySet()) {
            Player player = playerEntryEntry.getKey();
            Integer win = playerEntryEntry.getValue().getKey();
            Integer lose = playerEntryEntry.getValue().getValue();
            if (player.getName().equals("딜러")) {
                System.out.print(win + "승" + lose + "패\n");
            } else {
                System.out.println(win > 0 ? "패" : "승");
            }
        }

    }
}
