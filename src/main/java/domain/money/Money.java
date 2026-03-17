package domain.money;

public record Money(long amount) {

    public Money multiply(double factor) {
        return new Money(Math.round(amount * factor));
    }
}
