package blackjack.model.betting;

import java.util.Objects;

public class Money {
    private final int money;

    public Money(final int money) {
        validatePositiveNumber(money);
        this.money = money;
    }

    private void validatePositiveNumber(final int money) {
        if (money < 0) {
            throw new IllegalArgumentException("금액은 양수만 가능합니다.");
        }
    }

    public Money multiple(final double profitRate) {
        return new Money((int) (money * profitRate));
    }

    public Money add(final int profit) {
        return new Money(money + profit);
    }

    public Money payAll() {
        return new Money(0);
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money that = (Money) o;
        return money == that.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
