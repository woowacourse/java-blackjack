package blackjack.domain;

public class CardNumber {
    private static final int MAX_RANGE = 13;
    private static final int MIN_RANGE = 1;
    private final int value;

    public CardNumber(final int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(final int value) {
        if (value < MIN_RANGE || value > MAX_RANGE) {
            throw new IllegalArgumentException("범위를 초과하였습니다.");
        }
    }
}
