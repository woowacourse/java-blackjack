package blackjack.domain;

public enum PlayStatus {
    BUST,
    HIT,
    STAY;

    private static final int BUST_SCORE = 21;

    public static PlayStatus hitOrBust(int sum) {
        if (sum > BUST_SCORE) {
            return BUST;
        }

        return HIT;
    }

    public static boolean isBust(int score) {
        return score > BUST_SCORE;
    }
}
