package blackjack.model.betting;

public record BettingMoney(int amount) {
    private static final String INVALID_BETTING_MONEY_AMOUNT = "배팅 금액은 0원보다 커야 합니다.";

    public BettingMoney {
        validateAmount(amount);
    }

    private void validateAmount(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(INVALID_BETTING_MONEY_AMOUNT);
        }
    }
}
