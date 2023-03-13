package blackjack.domain.money;

import java.util.Objects;

public final class Money {

    private final int money;

    public Money(final int money) {
        this.money = money;
    }

    public Money add(final Money money) {
        return new Money(this.money + money.money);
    }

    public Money subtract(final Money money) {
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
