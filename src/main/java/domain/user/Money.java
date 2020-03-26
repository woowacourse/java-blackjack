package domain.user;

import java.util.Objects;

public class Money {
    private static final int MIN = 0;
    static final String OUT_OF_RANGE_MESSGAE = String.format("돈은 최소 %d원 이상이어야 합니다.", MIN);

    private final double value;

    private Money(double value) {
        this.value = value;
    }

    public static Money of(double value) {
        if (value < MIN) {
            throw new IllegalArgumentException(OUT_OF_RANGE_MESSGAE);
        }
        return new Money(value);
    }

    public Money multiply(double rate) {
        return new Money(value * rate);
    }

    public Money add(Money other) {
        return new Money(value + other.value);
    }

    public double getValue() {
        return value;
    }

    int serialize() {
        return (int) value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(money.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
