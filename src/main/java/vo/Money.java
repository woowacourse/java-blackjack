package vo;

import java.util.Objects;

public class Money {
    private final int value;

    public Money(int value) {
        validatePositive(value);
        this.value = value;
    }

    private void validatePositive(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 음수가 될 수 없습니다.");
        }
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
