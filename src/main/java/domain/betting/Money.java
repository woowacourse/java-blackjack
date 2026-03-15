package domain.betting;

import java.util.Objects;

public class Money {
    private final long value;
    private final int BETTING_UNIT = 1000;
    private final long MAX_BETTING_MONEY = 100_000_000L;

    public Money(long value) {
        validateMoney(value);
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    private void validateMoney(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException("베팅 금액은 양수입니다.");
        }
        if (value % BETTING_UNIT != 0) {
            throw new IllegalArgumentException(String.format("베팅 금액의 단위는 %s원 입니다.", BETTING_UNIT));
        }
        if (value > MAX_BETTING_MONEY) {
            throw new IllegalArgumentException(String.format("최대 베팅 액수는 %s원 입니다.", MAX_BETTING_MONEY));
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
