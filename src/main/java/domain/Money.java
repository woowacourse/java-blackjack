package domain;

public class Money {

    private final int value;

    public Money(int value) {
        validateNotNegative(value);
        this.value = value;
    }

    private void validateNotNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("금액은 음수 일 수 없습니다");
        }
    }

    public Money add(Money other) {
        return new Money(value + other.value);
    }

    public Money distribute(double dividend) {
        return new Money(value + (int)(value * dividend));
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
