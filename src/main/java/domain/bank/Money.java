package domain.bank;

public class Money {

    public static Money ZERO = Money.of(0);

    private final int value;

    private Money(int value) {
        this.value = value;
    }

    public static Money of(int value) {
        validateNotNegative(value);
        return new Money(value);
    }

    private static void validateNotNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("원금은 음수로 생성할 수 없습니다");
        }
    }

    public Money add(Money other) {
        return new Money(value + other.value);
    }

    public Money sub(Money other) {
        return new Money(value - other.value);
    }

    public Money multiply(double multiplier) {
        return new Money((int)(value * multiplier));
    }

    public boolean isLessThan(Money other) {
        return this.value < other.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Money money = (Money)o;

        return value == money.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
