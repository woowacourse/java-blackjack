package domain.batting;

import java.util.Objects;

public class Money {
    private final int value;

    public Money(int value) {
        validateMoney(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validateMoney(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("베팅 금액은 양수입니다.");
        }
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
