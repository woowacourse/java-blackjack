package blackjack.domain.player;

public class Money {

    private static final int DEFAULT_EARNING_RATE = 1;
    private final double value;

    public Money() {
        this(0);
    }

    public Money(double value) {
        this.value = value;
    }

    public Money sum(Money money) {
        if (money.isEmpty()) {
            return this;
        }
        return new Money(value + money.getValue());
    }

    public double getValue() {
        return value;
    }

    public Money profit(double earningRate) {
        if (earningRate == DEFAULT_EARNING_RATE) {
            return this;
        }
        return new Money(value * earningRate);
    }

    public Money negative() {
        return new Money(value * -1);
    }

    public boolean isEmpty() {
        return value == 0;
    }
}
