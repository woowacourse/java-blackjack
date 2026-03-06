package blackjack.domain.hand;

public class Score {

    private static final int BUST_THRESHOLD = 21;

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public boolean isBust() {
        return value > BUST_THRESHOLD;
    }

    public boolean isGreaterThan(final Score other) {
        return this.value > other.value;
    }

    public boolean isLessThan(final Score other) {
        return this.value < other.value;
    }

    public boolean isLessThanOrEqualTo(final Score other) {
        return this.value <= other.value;
    }

    public int getValue() {
        return value;
    }
}
