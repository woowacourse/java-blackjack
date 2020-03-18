package domain.money;

public class Money {
    private final double value;

    Money(double value) {
        this.value = value;
    }

    public Money multiply(double rate) {
        return new Money(value * rate);
    }
}
