package blackjack.domain.betting;

public class BettingAmount {
    private final int amount;

    public BettingAmount(final int amount) {
        validateRange(amount);
        this.amount = amount;
    }

    public void validateRange(final int amount) {
        if (amount < 1_000 || amount > 1_000_000) {
            throw new IllegalArgumentException("베팅 금액은 1,000원부터 1,000,000까지 입니다.");
        }
    }
}
