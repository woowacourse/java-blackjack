package blackjack.model.betting;

public record BettingMoney(int amount) {
    private static final String INVALID_BETTING_MONEY = "베팅 금액은 0원보다 커야 합니다.";

    private static final int BETTING_MONEY_LOWER_BOUND = 0;

    public BettingMoney {
        validateAmount(amount);
    }

    private void validateAmount(final int amount) {
        if (amount <= BETTING_MONEY_LOWER_BOUND) {
            throw new IllegalArgumentException(INVALID_BETTING_MONEY);
        }
    }
}
