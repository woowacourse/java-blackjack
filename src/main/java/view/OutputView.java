package view;

import domain.*;

import java.util.Map;

public class OutputView {
    public static final String DELIMITER = ", ";

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
        printUserCards(new Name("딜러"), dealer, withScore);
        printPlayersCards(players, withScore);
    }

    public static void printUserCards(Name name, User user, boolean withScore) {
        System.out.print(name + ": " + user.getCards());
        if (withScore) {
            System.out.print("- 결과: " + user.getScore());
        }
        System.out.println();
    }

    public static void printPlayersCards(Players players, boolean withScore) {
        for (Player player : players.getPlayers()) {
            printUserCards(player.getName(), player, withScore);
        }
    }

    public static void printFinalResult(Dealer dealer, Players players) {
        System.out.println("\n## 최종 승패");
        printDealerResult(dealer);
        for (Player player : players.getPlayers()) {
            printPlayerResult(player);
        }
    }

    private static void printPlayerResult(Player player) {
        System.out.println(player.getName() + ": " + player.getWinningResult().getKorean());
    }

    public static void printDealerHitMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    private static void printDealerResult(Dealer dealer) {
        System.out.println("딜러: " + makeDealerResult(dealer.getWinningResult()));
    }

    private static String makeDealerResult(Map<WinningResult, Integer> dealerWinningResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (WinningResult winningResult : WinningResult.values()) {
            int value = dealerWinningResult.get(winningResult);
            if (dealerWinningResult.get(winningResult) != 0) {
                stringBuilder.append(value);
                stringBuilder.append(winningResult.getKorean());
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}
