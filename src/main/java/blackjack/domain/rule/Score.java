package blackjack.domain.rule;

import java.util.Objects;

public final class Score {

    private static final int VALUE_MIN = 2;
    private static final int BLACKJACK_VALUE = 21;

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

    public boolean isBlackjack() {
        return value == BLACKJACK_VALUE;
    }

    public boolean isDead() {
        return value > BLACKJACK_VALUE;
    }

    public boolean isBiggerThan(final Score other) {
        return this.value > other.value;
    }

    public boolean isSmallerOrEqual(final Score score) {
        return this.value <= score.value;
    }

    public int toInt() {
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
