package blackjack.domain;

public class BetAmount {

    private final int amount;

    public BetAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 양수여야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
