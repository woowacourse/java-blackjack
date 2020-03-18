package view;

import domain.*;

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

    public static void printUserCards(User user, boolean withScore) {
        System.out.print(user.getName() + ": " + user.getCards());
        if (withScore) {
            System.out.print("- 결과: " + user.getScore());
        }
        System.out.println();
    }

    private static void printPlayersCards(Players players, boolean withScore) {
        for (Player player : players.getPlayers()) {
            printUserCards(player, withScore);
        }
    }

    public static void printFinalResult(Dealer dealer, Players players) {
        System.out.println("\n## 최종 승패");
        printDealerResult(dealer, players);
        for (Player player : players.getPlayers()) {
            printPlayerResult(dealer, player);
        }
    }

    private static void printPlayerResult(Dealer dealer, Player player) {
        System.out.println(player.getName() + ": " + player.getWinningResult(dealer.getScore()).getKorean());
    }

    public static void printDealerHitMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    private static void printDealerResult(Dealer dealer, Players players) {
        System.out.println(dealer.getName() + ": " + makeDealerResult(dealer.getWinningResult(players.getAllScore())));
    }

    private static String makeDealerResult(Map<WinningResult, Integer> dealerWinningResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (WinningResult winningResult : WinningResult.values()) {
            int winningResultCount = dealerWinningResult.get(winningResult);
            if (winningResultCount != 0) {
                stringBuilder.append(winningResultCount + winningResult.getKorean() + " ");
            }
        }
        return stringBuilder.toString();
    }
}
