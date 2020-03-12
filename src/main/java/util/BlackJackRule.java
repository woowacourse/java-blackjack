package util;

public class BlackJackRule {

    public static final int BLACK_JACK_SCORE = 21;
    public static final int DEALER_DRAW_BASIS_SCORE = 16;

    public static boolean isBlackJack(int score) {
        return score == BLACK_JACK_SCORE;
    }

    public static boolean isBust(int score) {
        return score > BLACK_JACK_SCORE;
    }

    public static boolean isDealerDraw(int score) {
        return score <= DEALER_DRAW_BASIS_SCORE;
    }
}
