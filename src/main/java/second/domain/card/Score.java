package second.domain.card;

import second.domain.score.ScoreCalculator;

import java.util.Objects;

public class Score {
    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public boolean isLargerThan(Score score) {
        return this.value > score.value;
    }

    public Score plus(Score value) {
        return new Score(this.value + value.value);
    }

    public Score plus(int value) {
        return new Score(this.value + value);
    }

    public boolean isBust() {
        return ScoreCalculator.BLACK_JACK_SCORE.isLargerThan(this);
    }

    public int getvalue() {
        return value;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    public int hashCode() {
        return Objects.hash(value);
    }
}
