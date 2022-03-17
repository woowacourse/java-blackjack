package blackjack.domain;

public class BettingAmount {

    private final int amount;

    public BettingAmount(int amount) {
        validateNegative(amount);
        this.amount = amount;
    }

    private void validateNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("배팅 금액은 음수이면 안됩니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
