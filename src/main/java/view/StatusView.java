package view;

import model.name.Name;

public class StatusView {

    public static void printPlayerResultStatus(final String name, final String cardNames, final int score) {
        System.out.println(name + "카드" + addDividerBeforeCardNames(cardNames) + addResultDividerBeforeScore(score));
    }

    public static void printPersonDefaultStatus(final String name, final String cardNames) {
        if (name.equals(Name.getDealer())) {
            printDealerDefaultStatus(name, cardNames);
            return;
        }
        printPlayerDefaultStatus(name, cardNames);
    }

    private static void printPlayerDefaultStatus(final String name, final String cardNames) {
        System.out.println(name + "카드" + addDividerBeforeCardNames(cardNames));
    }

    private static void printDealerDefaultStatus(final String name, final String cardNames) {
        System.out.println(name + addDividerBeforeCardNames(cardNames));
    }

    private static String addDividerBeforeCardNames(final String cardNames) {
        return ": " + cardNames;
    }

    private static String addResultDividerBeforeScore(final int score) {
        return " - 결과: " + score;
    }

}
