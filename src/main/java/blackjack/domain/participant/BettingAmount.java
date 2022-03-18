package blackjack.domain.participant;

public class BettingAmount {

    private final int amount;

    public BettingAmount(final int amount) {
        validateAmountNotNegative(amount);
        this.amount = amount;
    }

    private static void validateAmountNotNegative(final int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("베팅 금액은 0원보다 커야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }

}
