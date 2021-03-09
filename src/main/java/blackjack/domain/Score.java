package blackjack.domain;

public class Score {

    private static final int BLACKJACK = 21;
    private static final int TEN = 10;
    public static final Score BLACKJACK_SCORE = new Score(BLACKJACK);

    private int value;

    public Score(final int value) {
        this.value = value;
    }

    public Score plusTenIfNotBust() {
        final Score score = new Score(value + TEN);
        if (score.isBust()) {
            return this;
        }
        return score;
    }

    public boolean isBust() {
        return value > BLACKJACK;
    }

    public boolean isBlackjack() {
        return value == BLACKJACK;
    }
}
