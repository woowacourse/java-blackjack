package domain;

import java.util.Objects;

public class Score {
    private static final Score ZERO = new Score(0);
    private static final Score ACE_BONUS = new Score(10);
    private static final Score BUST_LIMIT = new Score(21);

    private final int value;

    public Score(int score) {
        this.value = score;
    }

    public Score add(Score other) {
        return new Score(value + other.value());
    }

    public Score addScoreByAce() {
        Score sumScore = add(ACE_BONUS);
        if (sumScore.value <= BUST_LIMIT.value) {
            return new Score(ACE_BONUS.value + value);
        }
        return this;
    }

    public static Score zero() {
        return ZERO;
    }

    public Score sum(Score other) {
        return new Score(this.value + other.value);
    }

    public boolean isGreaterThan(Score other) {
        return value > other.value;
    }

    public boolean isBusted() {
        return value > BUST_LIMIT.value;
    }

    public boolean isLessThan(int score) {
        return value < score;
    }

    public int value() {
        return value;
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
}
