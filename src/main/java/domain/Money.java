package domain;

public record Money(int amount) {
    public Money plus(int value) {
        return new Money(amount + value);
    }

    public Money minus(int value) {
        return new Money(amount - value);
    }
}
