package blackjack.domain;

public record Money(double value) {
    public Money toNegative() {
        return new Money(-value);
    }

    public Money multiply(final double multiplier) {
        return new Money(value * multiplier);
    }

    public boolean isInt() {
        return (int) value == value;
    }

    public int toInt() {
        return (int) value;
    }
}
