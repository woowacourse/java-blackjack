package blackjack.domain.user;

public class Score {
    private static final int BLACKJACK_VALUE = 21;
    private static final int TEN = 10;

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Score decideScoreByStatus() {
        Score score = new Score(value + TEN);
        if (score.isBust()) {
            return this;
        }
        return score;
    }

    private boolean isBust() {
        return value > BLACKJACK_VALUE;
    }

    private boolean isBlackjack() {
        return value == BLACKJACK_VALUE;
    }
}
