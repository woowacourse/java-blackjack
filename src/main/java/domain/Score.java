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

    public Score sub(Score target) {
        return new Score(value - target.value);
    }

    public boolean isGreaterThan(Score target) {
        return value > target.value;
    }

    public boolean isGreaterThanOrEqualTo(Score target) {
        return value >= target.value;
    }

    public boolean isGreaterThanOrEqualTo(int target) {
        return value >= target;
    }

    public boolean isLessThan(Score target) {
        return value < target.value;
    }

    public boolean isLessThanOrEqualTo(Score target) {
        return value <= target.value;
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
