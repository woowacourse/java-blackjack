package blackjack.domain.participant;

public class BettingAmount {

    private static final int DEFAULT_UNIT = 10;

    private final int amount;

    public BettingAmount(final int amount) {
        validateAmountPositive(amount);
        validateAmountIsDivisible(amount);
        this.amount = amount;
    }

    private static void validateAmountPositive(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 양수여야 합니다.");
        }
    }

    private static void validateAmountIsDivisible(final int amount) {
        if (amount % DEFAULT_UNIT != 0) {
            throw new IllegalArgumentException("베팅 금액은 10원 단위여야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }

}
