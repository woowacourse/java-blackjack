package view;

import java.util.List;

public class OutputView {
    private OutputView() {
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printSetDrawCard(List<String> playerNames) {
        System.out.println(String.format("\n딜러와 %s에게 2장의 카드를 나누었습니다.", String.join(",", playerNames)));
    }

    public static void userReport(String dealer, List<String> players) {
        userReport(dealer);
        for (String player : players) {
            userReport(player);
        }
        System.out.println();
    }

    public static void userReport(String player) {
        System.out.println(player);
    }

    public static void printDealerAdditionalCard() {
        System.out.println("\n딜러는 16이하라 카드를 한장 더 받았습니다.\n");
    }

    public static void printWinning(List<String> winningPlayerResult) {
        System.out.println("\n## 최종 수익");
        for (String result : winningPlayerResult) {
            System.out.println(result);
        }
    }
}
