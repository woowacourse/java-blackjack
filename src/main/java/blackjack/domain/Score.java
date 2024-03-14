package blackjack.domain;

import java.util.Objects;

public class Score {

    private static final int MAX_SCORE = 21;

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public boolean isAbove(Score target) {
        return this.value > target.value;
    }

    public boolean isMaxScore() {
        return value == MAX_SCORE;
    }

    public boolean isBustScore() {
        return value > MAX_SCORE;
    }

    @Override
    public boolean equals(Object o) {
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

    public int getValue() {
        return value;
    }
}
