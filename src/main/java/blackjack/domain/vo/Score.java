package blackjack.domain.vo;

import java.util.Objects;

public class Score {

    private final int value;

    private Score(int value) {
        this.value = value;
    }

    public static Score of(int value) {
        return new Score(value);
    }

    public Score add(Score other) {
        return new Score(this.value + other.value);
    }

    public boolean isGreaterOrEqualsTo(Score other) {
        return this.value >= other.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
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
}
