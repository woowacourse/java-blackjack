package blackjack.model.player;

public class BettingMoney {
    private final int amount;

    public BettingMoney(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 커야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }

}
