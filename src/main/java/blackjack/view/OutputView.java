package blackjack.view;

import java.util.List;

public class OutputView {
    private static final int DEALER_DRAW_BOUNDARY = 16;

    private OutputView() {
    }

    public static void printDefaultDrawCardMessage(final List<String> playerNames) {
        final String names = String.join(", ", playerNames);
        System.out.println("\n딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public static void printPersonStatus(final String playerName, final List<String> cards) {
        final String allCards = String.join(", ", cards);
        System.out.println(playerName + "카드 : " + allCards);
    }

    public static void printPersonStatus(final String playerName, final List<String> cards, int score) {
        final String allCards = String.join(", ", cards);
        System.out.println(playerName + "카드 : " + allCards + " - 결과: " + score);
    }

    public static void printDealerDrawCardMessage(final int score) {
        if (score > DEALER_DRAW_BOUNDARY) {
            System.out.println("\n딜러는 " + DEALER_DRAW_BOUNDARY + "초과라 카드를 받지 않았습니다.\n");
            return;
        }
        System.out.println("\n딜러는 " + DEALER_DRAW_BOUNDARY + "이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printGameEndMessage() {
        System.out.println("\n## 최종 수익");
    }

    public static void printProfitResult(final String playerName, final Double playerResult) {
        System.out.println(playerName + ": " + playerResult);
    }

    public static void printExceptionMessage(final String message) {
        System.out.println(message);
    }
}