package blackjack.domain.participants;

public class BettingMoney {

    private final int amount;

    public BettingMoney(final int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 큰 수여야 합니다.");
        }
    }
}
