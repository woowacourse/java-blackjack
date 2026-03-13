package blackjack.domain.result;

public record Score(int value) implements Comparable<Score> {

    @Override
    public int compareTo(Score other) {
        return Integer.compare(this.value, other.value);
    }

    public boolean isBiggerThan(Score other) {
        return compareTo(other) > 0;
    }

    public boolean isBiggerThan(final int otherValue) {
        return isBiggerThan(new Score(otherValue));
    }

    public boolean isLessThanOrEqual(final int otherValue) {
        return !isBiggerThan(otherValue);
    }
}
