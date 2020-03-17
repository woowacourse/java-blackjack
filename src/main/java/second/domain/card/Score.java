package second.domain.card;

import second.domain.score.ScoreCalculator;

import java.util.Objects;

import static second.domain.score.ScoreCalculator.BLACK_JACK_SCORE;

public class Score {
    private static final int BURST_SCORE = 0;
    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public boolean isLargerThan(Score score) {
        return this.value > score.value;
    }

    public Score plus(Score value) {
        if (this.value + value.value > BLACK_JACK_SCORE.value) {
            return new Score(BURST_SCORE);
        }
        return new Score(this.value + value.value);
    }

    public Score plus(int value) {
        if (this.value + value > BLACK_JACK_SCORE.value) {
            return new Score(BURST_SCORE);
        }
        return new Score(this.value + value);
    }

    public boolean isBust() {
        return this.value == BURST_SCORE;
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
