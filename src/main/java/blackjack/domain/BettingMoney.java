package blackjack.domain;

public class BettingMoney {

    private final int value;

    public BettingMoney(final int value) {
        validateBettingMoney(value);
        this.value = value;
    }

    private void validateBettingMoney(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("베팅 금액은 양수여야 합니다.");
        }
    }

    public int getValue() {
        return this.value;
    }
}
