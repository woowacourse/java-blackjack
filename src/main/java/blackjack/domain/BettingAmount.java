package blackjack.domain;

public class BettingAmount {

    private static final int VALUE_LOWER_BOUND = 1;
    private static final int VALUE_UPPER_BOUND = 1_000_000_000;

    private final double value;

    public BettingAmount(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        validateBound(value);
    }

    private void validateBound(final int value) {
        if (value < VALUE_LOWER_BOUND || value > VALUE_UPPER_BOUND) {
            throw new IllegalArgumentException("베팅 금액은 1이상 10억 이하여야 합니다.");
        }
    }

    public double getValue() {
        return value;
    }
}
