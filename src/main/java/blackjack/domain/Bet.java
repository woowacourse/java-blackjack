package blackjack.domain;

public class Bet {

    private final double value;

    public Bet(final double value) {
        validateBettingMoney(value);
        this.value = value;
    }

    private void validateBettingMoney(final double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("베팅 금액은 양수여야 합니다.");
        }
    }

    public double getValue() {
        return this.value;
    }
}
