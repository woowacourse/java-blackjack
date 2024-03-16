package blackjack.model.betting;

public record BettingMoney(int amount) {
    private static final String INVALID_BETTING_MONEY_AMOUNT = "배팅 금액은 0원 이상이어야 합니다.";

    public BettingMoney {
        if (amount < 0) {
            throw new IllegalArgumentException(INVALID_BETTING_MONEY_AMOUNT);
        }
    }
}
