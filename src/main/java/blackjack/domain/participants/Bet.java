package blackjack.domain.participants;

public class Bet {
    private final long amount;

    public Bet() {
        this.amount = 0L;
    }

    public Bet(long amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    private void validatePositive(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수여야 합니다.");
        }
    }
}
