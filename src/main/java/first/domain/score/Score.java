package first.domain.score;

import java.util.Objects;

public class Score implements Calculatable {
    private final int value;

    public Score(int value) {
        this.value = value;
    }

    @Override
    public Calculatable plus(Calculatable score) {
        return new Score(this.value + score.getValue());
    }

    @Override
    public boolean isLargerThan(Calculatable score) {
        return this.value > score.getValue();
    }

    @Override
    public boolean isLargerOrEqualThan(Calculatable score) {
        return this.value >= score.getValue();
    }

    @Override
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
