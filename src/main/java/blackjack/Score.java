package blackjack;

import java.util.List;
import java.util.Objects;

public class Score {

    private final int value;

    public int getValue() {
        return value;
    }

    public Score(int value) {
        this.value = value;
    }

    public boolean isBust() {
        return value > 21;
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

    public boolean lessThan(Score other) {
        return getValue() < other.getValue();
    }

    public boolean moreThan(Score other) {
        return getValue() > other.getValue();
    }
}
