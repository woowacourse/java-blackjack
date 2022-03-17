package blackjack.domain;

public enum PlayStatus {

    BUST,
    HIT,
    STAY;

    private static final int BUST_SCORE = 21;
    private static final int BLACKJACK_NUMBER_OF_CARDS = 2;

    public static PlayStatus of(int sum) {
        if (sum > BUST_SCORE) {
            return BUST;
        }

        if (sum == BUST_SCORE) {
            return STAY;
        }

        return HIT;
    }

    static boolean isBust(int score) {
        return score > BUST_SCORE;
    }

    public static boolean isBlackjack(int score, int numberOfCards) {
        return score == BUST_SCORE && numberOfCards == BLACKJACK_NUMBER_OF_CARDS;
    }
}
