package domain;

public record Money(int amount) {
    public Money plus(int value) {
        return new Money(amount + value);
    }
}
