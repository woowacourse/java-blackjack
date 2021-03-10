package blackjack.domain.user;

public class Score implements Comparable<Score> {
    private static final int BLACKJACK_VALUE = 21;
    private static final int DEALER_MUST_HIT_MAX_VALUE = 16;
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

    public boolean isBust() {
        return value > BLACKJACK_VALUE;
    }

    public boolean isBlackjack() {
        return value == BLACKJACK_VALUE;
    }

    public boolean isDealerMustToHitScore() {
        return value <= DEALER_MUST_HIT_MAX_VALUE;
    }

    @Override
    public int compareTo(Score anotherScore) {
        return Integer.compare(value, anotherScore.value);
    }
}
