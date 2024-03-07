package blackjack.domain;

import java.util.Objects;

public class Score {
    private static final int VALUE_MIN = 2;
    private final int value;

    public Score(final int score) {
        validate(score);
        this.value = score;
    }

    private void validate(final int score) {
        if (score <= VALUE_MIN) {
            throw new IllegalStateException("현재 갖고있는 카드의 합이 정상적이지 않습니다.");
        }
    }

    public boolean isBiggerThan(final Score other) {
        return this.value > other.value;
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
        final Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
