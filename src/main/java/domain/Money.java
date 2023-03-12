package domain;

public class Money {

    public static Money ZERO = Money.of(0);

    private final int principal;
    private final int value;

    private Money(int value) {
        this(value, value);
    }

    private Money(int principal, int value) {
        this.principal = principal;
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
        return new Money(principal, value + other.value);
    }

    public Money sub(Money other) {
        return new Money(principal, value - other.value);
    }

    public Money multiply(double multiplier) {
        return new Money(principal, (int)(value * multiplier));
    }

    public boolean isLessThan(Money other) {
        return this.value < other.value;
    }

    public int getProfit() {
        return value - principal;
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
