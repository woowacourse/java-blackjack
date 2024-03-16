package domain.player;

public class BetAmount {
    private static final int MAX_AMOUNT = 1_000_000;

    private final int value;

    public BetAmount(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        validateMaRange(value);
        validateNegativeNumber(value);
    }

    private void validateNegativeNumber(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException(String.format("배팅 금액: %d, 배팅 금액은 음수가 될 수 없습니다", value));
        }
    }

    private void validateMaRange(final int value) {
        if (value > MAX_AMOUNT) {
            throw new IllegalArgumentException(String.format("배팅 금액: %d, 배팅 금액은 최대 %d원입니다", value, MAX_AMOUNT));
        }
    }

    public int getValue() {
        return value;
    }
}
