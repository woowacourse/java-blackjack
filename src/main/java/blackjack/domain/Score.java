package blackjack.domain;

import java.util.Objects;

public class Score {
    private static final int BUST_SCORE = 21;

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isOverThen(Score other) {
        if (other.isBust()) {
            return true;
        }
        if (this.isBust()) {
            return false;
        }
        return this.value > other.value;
    }

    private boolean isBust() {
        return value > BUST_SCORE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Score)) {
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
