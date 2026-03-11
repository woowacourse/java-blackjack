package blackjack.domain.hand;

public record Score(int value) implements Comparable<Score> {

    private static final int BUST_THRESHOLD = 21;

    public boolean isBust() {
        return value > BUST_THRESHOLD;
    }

    public boolean isLessThanOrEqualTo(int hitThreshold) {
        return value <= hitThreshold;
    }

    @Override
    public int compareTo(Score score) {
        return Integer.compare(this.value, score.value);
    }
}
