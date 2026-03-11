package vo;

public class Money {
    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public int  getValue() {
        return value;
    }

    public Money multiply(double profitRate) {
        return new Money((int) (this.value * profitRate));
    }

    public Money subtract(Money other) {
        return new Money(this.value - other.value);
    }
}
