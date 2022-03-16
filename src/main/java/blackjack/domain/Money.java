package blackjack.domain;

public class Money {

    private int value;

    public Money() {
        this.value = 0;
    }

    public void init(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        validateBound(value);
        validateLastIndex(value);
    }

    private void validateBound(final int value) {
        if (value < 100 || value > 1_000_000_000) {
            throw new IllegalArgumentException("베팅 금액은 100이상 10억 이하여야 합니다.");
        }
    }

    private void validateLastIndex(final int value) {
        if (value % 10 != 0) {
            throw new IllegalArgumentException("베팅 금액은 10으로 나누어 떨어져야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    public void multiply() {
        value *= 1.5;
    }

    public void toZero() {
        value = 0;
    }

    public void toNegative() {
        value *= -1;
    }
}
