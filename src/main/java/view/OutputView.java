package view;

import domain.GameResult;
import domain.Outcome;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;
import java.util.Map;

public class OutputView {

    public static void printCardDistribution(Players players) {
        System.out
            .printf("\n딜러와 %s에게 2장의 카드를 나누었습니다.\n", String.join(", ", players.getPlayerNames()));
    }

    public static void printUsersCards(Dealer dealer, Players players) {
        System.out.println("딜러: " + dealer.getCards().getCards().get(0));
        for (Player player : players.getPlayers()) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    public static void printPlayerCards(Player player) {
        String playerCards = player.getCards().getCards().toString();
        System.out
            .println(player.getName() + ": " + playerCards.substring(1, playerCards.length() - 1));
    }

    public static void printDealerOneMoreCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printUsersCardsAndScore(Dealer dealer, Players players) {
        printUserCardsAndScore("딜러", dealer);
        for (Player player : players.getPlayers()) {
            printUserCardsAndScore(player.getName(), player);
        }
    }

    public static void printUserCardsAndScore(String name, User user) {
        String userCards = user.getCards().getCards().toString();
        System.out.println(name + ": " + userCards.substring(1, userCards.length() - 1)
            + " - 결과: " + user.getCards().getScore());
    }

    public static void printFinalResult(GameResult gameResult) {
        System.out.println("\n## 최종 승패");
        printDealerFinalResult(gameResult.getDealerResults());
        for (Player player : gameResult.getPlayersResult().keySet()) {
            System.out.println(
                player.getName() + ": " + gameResult.getPlayersResult().get(player).getName());
        }
    }

    private static void printDealerFinalResult(Map<Outcome, Integer> gameResult) {
        System.out.print("딜러: ");
        for (Outcome outcome : gameResult.keySet()) {
            if (gameResult.get(outcome) != 0) {
                System.out.print(gameResult.get(outcome) + outcome.getName() + " ");
            }
        }
        System.out.println();
    }
}
