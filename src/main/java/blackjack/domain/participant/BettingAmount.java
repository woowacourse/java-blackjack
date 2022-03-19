package blackjack.domain.participant;

public class BettingAmount {

    private static final int ALLOWED_MINIMUM_BETTING_AMOUNT = 10;

    private final int amount;

    public BettingAmount(final int amount) {
        validateAmountNotNegative(amount);
        this.amount = amount;
    }

    private static void validateAmountNotNegative(final int amount) {
        if (amount < ALLOWED_MINIMUM_BETTING_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 10원보다 커야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }

}
