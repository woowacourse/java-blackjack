package blackjack.domain.participant;

public class Bet {

    private final double amount;

    public Bet(int amount) {
        this.amount = amount;
        validateNegative(this.amount);
    }

    private void validateNegative(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액 1원 이상이어야 합니다.");
        }
    }
}
