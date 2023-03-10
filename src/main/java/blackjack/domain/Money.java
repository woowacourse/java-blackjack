package blackjack.domain;

import java.util.Objects;

public final class Money {

    static final String SUBTRACT_ERROR_MESSAGE = "현재 금액보다 더 큰 금액을 뺄 수는 없습니다.";

    private final int money;

    public Money(final int money) {
        this.money = money;
    }

    public Money add(final Money money) {
        return new Money(this.money + money.money);
    }

    public Money subtract(final Money money) {
        if (this.money < money.money) {
            throw new IllegalArgumentException(SUBTRACT_ERROR_MESSAGE);
        }
        return new Money(this.money - money.money);
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
