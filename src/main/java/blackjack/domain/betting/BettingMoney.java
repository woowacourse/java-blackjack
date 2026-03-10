package blackjack.domain;

public class BettingMoney {

    private static final String INVALID_AMOUNT_MESSAGE = "배팅 금액은 양수여야 합니다.";

    private final int amount;

    public BettingMoney(final int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(INVALID_AMOUNT_MESSAGE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
