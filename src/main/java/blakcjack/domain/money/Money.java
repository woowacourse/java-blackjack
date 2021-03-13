package blakcjack.domain.money;

public class Money {
    private final double value;

    public Money(final double value) {
        this.value = value;
    }

    public int toInt() {
        return (int) value;
    }

    public double toDouble() {
        return value;
    }
}
