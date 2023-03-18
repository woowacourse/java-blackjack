package blackjack.domain;

public class BettingMoney {
    private static final int MIN_VALUE = 100;
    private static final int MAX_VALUE = 1000000;
    private static final int STATE_MULTIPLE = 10;
    private final int value;

    public BettingMoney(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        validateRange(value);
        validateMultiple(value);
    }

    private void validateRange(final int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException("상금의 최소값은 100 최대 값은 1,000,000입니다.");
        }
    }

    private void validateMultiple(final int value) {
        if (value % STATE_MULTIPLE != 0) {
            throw new IllegalArgumentException("상금은 10배수로 나누어 떨어져야 합니다.");
        }
    }

    public int getMoney() {
        return value;
    }
}
