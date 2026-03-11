package domain;

import java.util.Objects;

public class Score {
    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public Score add(Score target) {
        return new Score(target.value + this.value);
    }

    public boolean isLessThanOrEqualTo(Score target) {
        return value <= target.value;
    }

    public int getValue() {
        return value;
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

    public boolean isGreaterThan(Score dealerSum) {
        return value > dealerSum.value;
    }

    public boolean isLessThan(Score dealerSum) {
        return value < dealerSum.value;
    }
}
