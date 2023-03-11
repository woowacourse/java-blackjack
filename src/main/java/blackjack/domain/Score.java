package blackjack.domain;

import java.util.Arrays;
import java.util.Objects;

public class Score {
    public static final int BLACKJACK_SCORE = 21;

    private final int value;

    public static Score of(int... values) {
        int sum = Arrays.stream(values).sum();
        return new Score(sum);
    }

    public Score(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("점수는 음수일 수 없습니다.");
        }
        this.value = value;
    }

    public Score getValueIncludingAce() {
        if (value > BLACKJACK_SCORE) {
            return new Score(value - 10);
        }
        return this;
    }

    public boolean isBlackjack() {
        return value == BLACKJACK_SCORE;
    }

    public boolean isBust() {
        return value > BLACKJACK_SCORE;
    }

    public boolean isHigherThan(Score other) {
        return value > other.value;
    }

    public int getValue() {
        return value;
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
}
