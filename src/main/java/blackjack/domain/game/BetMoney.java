package blackjack.domain.game;

public class BetMoney {
    static final String INVALID_ACCOUNT_RANGE_MESSAGE = "배팅 금액은 1,000 이상 100,000,000 이하여야 합니다. 입력값 :";
    private static final int MINIMUM_AMOUNT = 1_000;
    private static final int MAXIMUM_AMOUNT = 100_000_000;

    private final int amount;

    public BetMoney(final int amount) {
        validateAmountRange(amount);
        this.amount = amount;
    }

    private void validateAmountRange(final int amount) {
        if (amount < MINIMUM_AMOUNT || amount > MAXIMUM_AMOUNT) {
            throw new IllegalArgumentException(INVALID_ACCOUNT_RANGE_MESSAGE + amount);
        }
    }

    public int getAmount() {
        return amount;
    }
}
