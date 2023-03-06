package view;

import java.util.List;

public final class OutputView {

    private static final String DELIMITER = ", ";

    public static void printPlayerCards(final String name, final List<String> cards) {
        final String playerCards = convertCardsFormat(cards);
        System.out.printf("%s 카드: %s%n", name, playerCards);
    }

    public static void printSetupGame(final List<String> names) {
        final String participants = String.join(DELIMITER, names);
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", participants);
    }

    public static void printDealerHit() {
        System.out.printf("%n딜러는 16이하라 한장의 카드를 더 받았습니다.%n");
    }

    public static void printPlayerScore(final String name, final List<String> cards, final int playerScore) {
        final String playerCards = convertCardsFormat(cards);
        System.out.printf("%s 카드: %s - 결과: %d%n", name, playerCards, playerScore);

    }

    public static void printGameResult(final List<String> winners, final List<String> losers) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        System.out.printf("딜러: %d승 %d패%n", winners.size(), losers.size());

        for (String winner : winners) {
            System.out.printf("%s: 승%n", winner);
        }
        for (String loser : losers) {
            System.out.printf("%s: 패%n", loser);
        }
    }

    public static void printExceptionMessage(final String message) {
        System.out.println(message);
    }

    private static String convertCardsFormat(final List<String> cards) {
        return String.join(DELIMITER, cards);
    }
}
