package domain.score;

import java.util.Objects;

public final class Score {

    private static final Score MAX = new Score(21);

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public Score add(Score score) {
        return new Score(this.value + score.value);
    }

    public boolean isMax() {
        return this.isEquals(MAX);
    }

    public boolean isEquals(final Score score) {
        return this.equals(score);
    }

    public boolean isGreaterThan(final Score score) {
        return this.value > score.value;
    }

    public boolean isSmallerOrEqualsTo(final Score score) {
        return this.value <= score.value;
    }

    public boolean isSmallerThan(final Score score) {
        return this.value < score.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
