package blackjack.domain.hand;

import java.util.Objects;

public class Score {

    private static final int BLACKJACK = 21;
    private static final int DEALER_HIT_THRESHOLD = 16;

    private final int value;

    public Score(int value) {
        validateNotNegative(value);
        this.value = value;
    }

    private void validateNotNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("[ERROR] 점수는 음수일 수 없습니다.");
        }
    }

    public Score add(Score other) {
        return this.add(other.value);
    }

    public Score add(int value) {
        return new Score(this.value + value);
    }

    public boolean isBlackjack() {
        return this.value == BLACKJACK;
    }

    public boolean isBust() {
        return value > BLACKJACK;
    }

    public boolean isPlayerHit() {
        return !isBust();
    }

    public boolean isDealerHit() {
        return this.value <= DEALER_HIT_THRESHOLD;
    }

    public boolean isBiggerThan(Score other) {
        return this.value > other.value;
    }

    public boolean isLessThan(Score other) {
        return this.value < other.value;
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
