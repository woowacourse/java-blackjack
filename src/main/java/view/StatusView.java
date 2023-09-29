package view;

public class StatusView {

    public static void printPlayerResultStatus(final String name, final String cardNames, final int score) {
        System.out.println(name + "카드" + addDividerBeforeCardNames(cardNames) + addResultDividerBeforeScore(score));
    }

    public static void playerDefaultAnswer(final String name, final String cardNames) {
        System.out.println(name + "카드" + addDividerBeforeCardNames(cardNames));
    }

    public static void dealerDefaultAnswer(final String name, final String cardNames) {
        System.out.println(name + addDividerBeforeCardNames(cardNames));
    }

    private static String addDividerBeforeCardNames(final String cardNames) {
        return ": " + cardNames;
    }

    private static String addResultDividerBeforeScore(final int score) {
        return " - 결과: " + score;
    }

}
