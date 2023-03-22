package blackjack.domain.participants;

import java.util.Objects;

public class Money {

    // 돈이 음수가 되는 경우
    // 베팅 머니는 10원단위여야함.
    private final double value;

    public Money(double value) {
        this.value = value;
    }

    public Money add(Money add) {
        return new Money(value + add.value);
    }

    public Money multiple(double profit) {
        return new Money(value * profit);
    }

    public boolean isDividedBy(Money divide) {
        return (int) (value % divide.value) == 0;
    }

    public boolean isBiggerThan(Money other) {
        return value > other.value;
    }

    public double getValue() {
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
        Money money = (Money) o;
        return Double.compare(money.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
