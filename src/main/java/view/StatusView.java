package view;

import static util.Keyword.DEALER;

public class StatusView {

    public static void printPersonDefaultStatus(final String name, final String cardNames) {
        if (name.equals(DEALER.getValue())) {
            System.out.println(dealerDefaultStatus(name, cardNames));
            return;
        }
        System.out.println(playerDefaultStatus(name, cardNames));
    }

    public static void printPlayerResultStatus(final String name, final String cardNames, final int score) {
        System.out.println(playerDefaultStatus(name, cardNames) + addResultDividerBeforeScore(score));
    }

    private static String playerDefaultStatus(final String name, final String cardNames) {
        return name + "카드" + addDividerBeforeCardNames(cardNames);
    }

    private static String dealerDefaultStatus(final String name, final String cardNames) {
        return name + addDividerBeforeCardNames(cardNames);
    }

    private static String addDividerBeforeCardNames(final String cardNames) {
        return ": " + cardNames;
    }

    private static String addResultDividerBeforeScore(final int score) {
        return " - 결과: " + score;
    }
}
