package blackjack.domain.result;

import java.util.Objects;

public class Score {

    public static final int BLACKJACK_NUMBER = 21;
    public static final int ACE_NUMBER = 1;
    public static final int ALTERNATE_ACE_VALUE = 10;

    private final int value;

    public Score(int value) {
        this.value = value;
        validateNegative(value);
    }

    private void validateNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("점수는 음수일 수 없습니다.");
        }
    }

    public Score calculateWithAce() {
        if (value + ALTERNATE_ACE_VALUE > BLACKJACK_NUMBER) {
            return new Score(value);
        }
        return new Score(value + ALTERNATE_ACE_VALUE);
    }

    public boolean isOver(Score score) {
        return this.value > score.value;
    }

    public boolean isBelow(Score score) {
        return this.value < score.value;
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

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                '}';
    }
}
