package domain;

import java.util.Objects;

public class Score {

    private static final int UPPER_LIMIT_SCORE = 21;

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score score1 = (Score) o;
        return value == score1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public boolean isLessThan(final Score other) {
        return value < other.value;
    }

    public boolean isBust() {
        return value > UPPER_LIMIT_SCORE;
    }

    public boolean canMoreCard() {
        return value < UPPER_LIMIT_SCORE;
    }

    public boolean isLessEqualThan(final Score other) {
        return value <= other.value;
    }

    public boolean isGreaterThan(final Score other) {
        return value > other.value;
    }

    public int value() {
        return value;
    }
}
