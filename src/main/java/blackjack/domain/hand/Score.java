package blackjack.domain.hand;

public record Score(int value) {

    private static final int BUST_THRESHOLD = 21;

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
}
