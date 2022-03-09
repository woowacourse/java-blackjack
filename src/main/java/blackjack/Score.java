package blackjack;

import java.util.Objects;

public class Score {

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public Result compare(Score other) {
        if (hasBust(other)) {
            return compareBustCase(other);
        }
        return compareNormalCase(other);
    }

    public boolean isBust() {
        return value > 21;
    }

    private boolean hasBust(Score other) {
        return isBust() || other.isBust();
    }

    private Result compareBustCase(Score other) {
        if (isBust() && other.isBust()) {
            return Result.DRAW;
        } else if (isBust()) {
            return Result.LOSS;
        }
        return Result.WIN;
    }

    private Result compareNormalCase(Score other) {
        if (value > other.value) {
            return Result.WIN;
        } else if (value == other.value) {
            return Result.DRAW;
        }
        return Result.LOSS;
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

    @Override
    public String toString() {
        return "Score{" +
            "value=" + value +
            '}';
    }
}
