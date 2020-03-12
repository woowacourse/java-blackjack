package util;

public class BlackJackRule {

    private static final int BLACK_JACK_SCORE = 21;

    public static boolean isBlackJack(int score) {
        return score == BLACK_JACK_SCORE;
    }

    public static boolean isBust(int score) {
        return score > BLACK_JACK_SCORE;
    }

    public static boolean isOverSixteen(int score) {
        return score > 16;
    }
}
