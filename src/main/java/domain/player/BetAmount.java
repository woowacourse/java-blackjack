package domain.player;

public record BetAmount(int value) {

    private static final int MIN_AMOUNT = 100;
    private static final int MAX_AMOUNT = 1_000_000;
    public static final BetAmount LOWEST = new BetAmount(MIN_AMOUNT);

    public BetAmount {
        validateRange(value);
    }

    private void validateRange(final int value) {
        if (value < MIN_AMOUNT) {
            throw new IllegalArgumentException(String.format("배팅 금액: %d, 배팅 금액은 최소 %d 이상입니다", value, MIN_AMOUNT));
        }
        if (value > MAX_AMOUNT) {
            throw new IllegalArgumentException(String.format("배팅 금액: %d, 배팅 금액은 최대 %d 이하입니다", value, MAX_AMOUNT));
        }
    }
}
