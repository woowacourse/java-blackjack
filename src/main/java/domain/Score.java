package domain;

public record Score(int value) implements Comparable<Score> {
    private static final int BUST_THRESHOLD = 21;

    public boolean isBust() {
        return value > BUST_THRESHOLD;
    }

    @Override
    public int compareTo(Score other) {
        return Integer.compare(this.value, other.value);
    }
}
