package domain.card;

import java.util.Objects;

public class Score {

    private static final Score ACE_SUBTRACTION = new Score(10);
    private static final Score BLACKJACK = new Score(21);
    private static final Score MIN = new Score(0);

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public static Score min() {
        return MIN;
    }

    public static Score blackjack() {
        return BLACKJACK;
    }

    public Score minusTenIfBust() {
        if (isMoreThan(BLACKJACK)) {
            return sub(ACE_SUBTRACTION);
        }

        return this;
    }

    public boolean isMoreThan(Score other) {
        return value > other.value;
    }

    public boolean isOverMax() {
        return isMoreThan(BLACKJACK);
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
        return BLACKJACK.equals(this);
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
