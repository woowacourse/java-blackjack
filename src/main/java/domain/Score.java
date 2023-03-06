package domain;

import java.util.Objects;

public class Score {

    private static final int UPPER_LIMIT_SCORE = 21;

    private final int score;

    public Score(final int score) {
        this.score = score;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    public boolean isLessThan(final Score other) {
        return score - other.score < 0;
    }

    public boolean isBust() {
        return score > UPPER_LIMIT_SCORE;
    }

    public boolean canMoreCard() {
        return score < UPPER_LIMIT_SCORE;
    }
}
