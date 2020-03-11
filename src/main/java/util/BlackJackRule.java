package util;

public class BlackJackRule {
    public static boolean isBlackJack(int score) {
        return score == 21;
    }

    public static boolean isBust(int score) {
        return score > 21;
    }

    public static boolean isOverSixteen(int score) {
        return score > 16;
    }
}
