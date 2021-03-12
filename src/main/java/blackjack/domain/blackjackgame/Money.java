package blackjack.domain.blackjackgame;

public class Money {

    private final double value;

    public Money() {
        this(0);
    }

    public Money(double value) {
        this.value = value;
    }

    public Money sum(Money money) {
        return new Money(value + money.getValue());
    }

    public double getValue() {
        return value;
    }

    public Money profit(double earningRate) {
        return new Money(value * earningRate);
    }

    public Money negative() {
        return new Money(value * -1);
    }

}
