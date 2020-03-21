package second.domain.score;

import java.util.Objects;

public class Score {
    private static final int BURST_SCORE = 0;
    private static final int BLACK_JACK_MAX_SCORE = 21;

    private final int value;

    public Score(final int value) {
        if (value > BLACK_JACK_MAX_SCORE) {
            this.value = BURST_SCORE;
            return;
        }
        this.value = value;
    }

    public boolean isLargerThan(final Score score) {
        return this.value > score.value;
    }

    public Score plus(final Score value) {
        return new Score(this.value + value.value);
    }

    public boolean isMaxScore() {
        return this.value == BLACK_JACK_MAX_SCORE;
    }

    public boolean isBust() {
        return this.value == BURST_SCORE;
    }

    public boolean isSameAs(Score score) {
        return this.value == score.value;
    }

    public int getValue() {
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
