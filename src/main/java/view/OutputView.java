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
        System.out.println("딜러: " + dealer.getCardsInfos().get(0));
        for (Player player : players.getPlayers()) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    public static void printPlayerCards(Player player) {
        System.out.println(player.getName() + ": " + String.join(", ", player.getCardsInfos()));
    }

    public static void printDealerOneMoreCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printUsersCardsAndScore(Dealer dealer, Players players) {
        System.out.println();
        printUserCardsAndScore("딜러", dealer);
        for (Player player : players.getPlayers()) {
            printUserCardsAndScore(player.getName(), player);
        }
    }

    public static void printUserCardsAndScore(String name, User user) {
        System.out.println(name + ": " + String.join(", ", user.getCardsInfos())
            + " - 결과: " + user.getScore());
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
