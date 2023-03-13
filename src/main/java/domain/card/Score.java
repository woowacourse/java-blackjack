package domain.card;

import java.util.Objects;

public class Score {

    private static final Score aceSubtraction = new Score(10);
    private static final Score max = new Score(21);
    private static final Score min = new Score(0);

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public static Score min() {
        return min;
    }

    public static Score max() {
        return max;
    }

    public Score minusTenIfBust() {
        if (isMoreThan(max)) {
            return sub(aceSubtraction);
        }

        return this;
    }

    public boolean isMoreThan(Score other) {
        return value > other.value;
    }

    public boolean isOverMax() {
        return isMoreThan(max);
    }

    public Score sum(Score other) {
        return new Score(value + other.value);
    }

    public Score sub(Score other) {
        return new Score(value - other.value);
    }

    public int value() {
        return value;
    }

    public boolean isLessThanOrEqual(Score other) {
        return value <= other.value;
    }

    public boolean isMax() {
        return max.equals(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                '}';
    }
}
