package blackjack.domain.card;

public class Score {
    public static final int MAX_SCORE_VALUE = 21;
    private final int value;

    public Score(int value) {
        validateRange(value);
        this.value = valueByUpperLimit(value);
    }

    private void validateRange(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("점수는 0 이상 입니다.");
        }
    }

    private int valueByUpperLimit(final int value) {
        if (value > MAX_SCORE_VALUE) {
            return 0;
        }
        return value;
    }

    public int getValue() {
        return value;
    }
}
