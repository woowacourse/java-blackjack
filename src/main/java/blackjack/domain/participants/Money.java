package blackjack.domain.participants;

public class Money {

    private final double value;

    public Money(final double value) {
        validateNonZeroPositiveNumber(value);
        this.value = value;
    }

    private void validateNonZeroPositiveNumber(final double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0원보다 많아야 합니다.");
        }
    }
}
