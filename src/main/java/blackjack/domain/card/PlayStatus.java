package blackjack.domain.card;

public enum PlayStatus {
    BUST,
    HIT,
    STAY;

    public static final int BUST_SCORE = 21;

    public static PlayStatus hitOrBust(int sum) {
        if (sum > BUST_SCORE) {
            return BUST;
        }

        return HIT;
    }
}
