package util;

public class BlackJackRule {
    private static final int BLACK_JACK_SCORE = 21;
    private static final int DEALER_HIT_MAX_SCORE = 16;

    public static boolean isBlackJack(int score) {
        return score == BLACK_JACK_SCORE;
    }

    public static boolean isBust(int score) {
        return score > BLACK_JACK_SCORE;
    }

    public static boolean canDealerHit(int score) {
        return score <= DEALER_HIT_MAX_SCORE;
    }
}
