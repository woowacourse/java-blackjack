package blackjack.model.betting;

public record Money(int amount) {
    private static final String INVALID_MONEY_AMOUNT = "금액은 0 이상이어야 합니다.";

    public Money {
        if (amount < 0) {
            throw new IllegalArgumentException(INVALID_MONEY_AMOUNT);
        }
    }
}
