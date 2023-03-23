package domain.money;

import java.util.Objects;

public class Money {

    private final int value;

    protected Money(int value) {
        this.value = value;
    }

    public static Money valueOf(int value) {
        return new Money(value);
    }

    public int multiply(double profitRate) {
        return (int) ((double) value * profitRate);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money that = (Money) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
