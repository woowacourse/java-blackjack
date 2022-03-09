package blackjack;

import java.util.Objects;

public class Score implements Comparable<Score> {

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

    public Score compare(Score other) {
        if (isBust() && other.isBust()) {
            return this;
        }

        if (isBust()) {
            return other;
        }

        if (other.isBust()) {
            return this;
        }

        return value > other.value ? this : other;
    }

    @Override
    public int compareTo(Score score) {
        return Integer.compare(getValue(), score.getValue());
    }
}
