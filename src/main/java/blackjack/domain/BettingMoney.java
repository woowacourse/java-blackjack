package blackjack.domain;

public class BettingMoney {
    private final int value;

    public BettingMoney(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        validateNotPositive(value);
        validateInappropriateUnit(value);
    }

    private void validateNotPositive(int uncheckedValue) {
        if (uncheckedValue <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 커야 합니다.");
        }
    }

    private void validateInappropriateUnit(int uncheckedValue) {
        if(uncheckedValue % 100 != 0) {
            throw new IllegalArgumentException("베팅 금액은 100 단위어야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
