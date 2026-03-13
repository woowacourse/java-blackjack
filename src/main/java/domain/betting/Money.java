package domain.betting;

import java.util.Objects;

public class Money {
    private final long value;

    public Money(long value) {
        validateMoney(value);
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    private void validateMoney(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("베팅 금액은 양수입니다.");
        }
    }

    public Money sum(Money other) {
        return new Money(this.value + other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
