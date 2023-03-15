package blackjackgame.domain;

import java.util.Objects;

public class Score {
    private static final Score min = new Score(0);
    private static final Score ace_additional = new Score(10);
    private static final Score max = new Score(21);

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public static Score min() {
        return min;
    }

    public boolean isMax() {
        return max.equals(this);
    }

    public Score plusTenIfNotBurst() {
        final var sumScore = add(ace_additional);
        if (sumScore.isLessThanOrEqual(max)) {
            return sumScore;
        }
        return this;
    }

    public boolean isLessThanOrEqual(Score other) {
        return this.isLessThan(other) || value == other.value;
    }

    private boolean isLessThan(Score other) {
        return value < other.value;
    }

    public Score add(final Score other) {
        return new Score(value + other.value);
    }

    @Override
    public String toString() {
        return "blackjack.Score{" +
            "value=" + value +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Score score = (Score)o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public boolean isOverMax() {
        return max.isLessThan(this);
    }

    public int value() {
        return value;
    }
}
