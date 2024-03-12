package blackjack.domain;

import java.util.Objects;

public class Score {
    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public Score add(Score other) {
        return new Score(this.value + other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Score score)) {
            return false;
        }
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
