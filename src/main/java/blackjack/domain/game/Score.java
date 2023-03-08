package blackjack.domain.game;

import java.util.Objects;

public class Score {

    private static final int BLACKJACK = 21;

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public Score minus(int value) {
        return new Score(this.value - value);
    }

    public boolean isHit() {
        return value <= BLACKJACK;
    }

    public boolean isHit(int hitScore) {
        return value <= hitScore;
    }

    public boolean isBust() {
        return value > BLACKJACK;
    }

    public boolean isGreaterThan(Score other) {
        return value > other.value;
    }

    public boolean isEqualsTo(Score other) {
        return value == other.value;
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
