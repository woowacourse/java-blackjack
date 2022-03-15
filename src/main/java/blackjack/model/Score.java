package blackjack.model;

import java.util.Objects;

public class Score {

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isBust() {
        return value > 21;
    }

    public boolean lessThan(Score other) {
        return getValue() < other.getValue();
    }

    public boolean moreThan(Score other) {
        return getValue() > other.getValue();
    }

    public Score plus(Score score) {
        return new Score(value + score.getValue());
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
