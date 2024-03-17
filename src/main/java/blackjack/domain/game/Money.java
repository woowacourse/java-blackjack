package blackjack.domain.game;

public record Money(int value) {

    public Money() {
        this(0);
    }

    public Money multipleRatio(double ratio) {
        return new Money((int) (this.value * ratio));
    }

    public Money add(Money money) {
        return new Money(value + money.value);
    }

    public Money negate() {
        return new Money(-this.value);
    }
}
