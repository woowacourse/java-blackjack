package domain;

public record Money(long amount) {

    public Money multiply(double factor) {
        return new Money(Math.round(amount * factor));
    }
}
