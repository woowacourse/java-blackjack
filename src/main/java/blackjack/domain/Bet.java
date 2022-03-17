package blackjack.domain;

public class Bet {

    private final double amount;

    public Bet(double amount) {
        this.amount = amount;
        validateNegative(amount);
    }

    private void validateNegative(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("베팅 금액은 음수일 수 없습니다.");
        }
    }
}
