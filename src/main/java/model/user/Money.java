package model.user;

import java.util.Objects;

public class Money {

    private final Long money;

    public Money(final Long money) {
        this.money = money;
    }

    public Money lose() {
        return new Money(oppositeMoney());
    }

    private Long oppositeMoney() {
        return -money;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Money money1 = (Money) o;
        return Objects.equals(money, money1.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    @Override
    public String toString() {
        return "Money{" +
                "money=" + money +
                '}';
    }
}
