package blackjack.domain.money;

public record Money(int value) {

    public Money add(Money money) {
        return new Money(value + money.value);
    }

    public Money multiply(double multiplier) {
        return new Money((int) (value * multiplier));
    }

    public Money negate() {
        return new Money(-value);
    }
}
