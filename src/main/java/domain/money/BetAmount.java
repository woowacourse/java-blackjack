package domain.money;

public record BetAmount(int value) {
    private static final int MIN_BET_AMOUNT = 1000;

    public BetAmount {
        validate(value);
    }

    private void validate(int value) {
        if (value < MIN_BET_AMOUNT) {
            throw new IllegalArgumentException("금액은 최소 %d 이상이여야 합니다: %d".formatted(MIN_BET_AMOUNT, value));
        }
    }
}
