package domain.result;

import java.util.Objects;

public class Score {
    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public Score plus(Score score) {
        return new Score(this.value + value);
    }

    public boolean isBiggerThan(Score score) {
        return this.value > score.value;
    }

    public boolean isLowOrEqualThan(Score score) {
        return this.value <= score.value;
    }

    public int getValue() {
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
