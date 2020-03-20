package view;

import domain.*;
import domain.gambler.*;

import java.util.Map;

public class OutputView {
    private static final String DELIMITER = ", ";

    public static void printFirstCards(Dealer dealer, Players players) {
        printFirstDrawMessage(players);
        printFirstDealerCards(dealer);
        printPlayersCards(players, false);
        System.out.println();
    }

    private static void printFirstDrawMessage(Players players) {
        System.out.println("\n딜러와 " + String.join(DELIMITER, players.getPlayerNames().toString())
                + "에게 2장의 카드를 나누었습니다.");
    }

    private static void printFirstDealerCards(Dealer dealer) {
        System.out.println("딜러: " + dealer.getFirstCard());
    }

    public static void printUsersCards(Dealer dealer, Players players, boolean withScore) {
        printUserCards(dealer, withScore);
        printPlayersCards(players, withScore);
    }

    public static void printUserCards(Gambler gambler, boolean withScore) {
        System.out.print(gambler.getName() + ": " + gambler.getCards());
        if (withScore) {
            System.out.print("- 결과: " + gambler.getScore());
        }
        System.out.println();
    }

    private static void printPlayersCards(Players players, boolean withScore) {
        for (Player player : players.getPlayers()) {
            printUserCards(player, withScore);
        }
    }

    public static void printFinalResult(Dealer dealer, ProfitResult profitResult) {
        System.out.println("\n## 최종 승패");
        printDealerResult(dealer, profitResult.getDealerResult());
        printPlayerResult(profitResult.getPlayerResults());
    }

    private static void printPlayerResult(Map<Player, Money> playersResult) {
        for (Player player : playersResult.keySet()) {
            Money playerMoney = playersResult.get(player);
            System.out.printf("%s: %s", player.getName(), playerMoney);
            System.out.println();
        } }

    public static void printDealerHitMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    private static void printDealerResult(Dealer dealer, Money dealerMoney) {
        System.out.printf("%s: ", dealer.getName());
        System.out.println(dealerMoney);
    }
}
