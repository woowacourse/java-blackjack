package blackjack.domain.result;

public record Money(double value) {
    public Money toNegative() {
        return new Money(-value);
    }

    public Money multiply(final double multiplier) {
        return new Money(value * multiplier);
    }
}
