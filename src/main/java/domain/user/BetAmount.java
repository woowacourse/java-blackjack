package domain.user;

public class BetAmount {

    private static final int MIN_BET_AMOUNT = 1;

    private static final String BET_AMOUNT_SMALL_MESSAGE =
            String.format("[ERROR] 배팅 금액은 %d이상이어야 합니다.", MIN_BET_AMOUNT);

    private final int amount;

    public BetAmount(final int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(BET_AMOUNT_SMALL_MESSAGE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
