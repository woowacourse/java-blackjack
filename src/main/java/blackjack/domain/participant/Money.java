package blackjack.domain.participant;

import java.util.Objects;

public class Money {
    private final int money;

    public Money(final int money) {
        this.money = money;
    }

    public int calculateProfit(double profitRate) {
        return (int) (money * profitRate);
    }

    public Money updateMoneyByProfit(final int profit) {
        return new Money(money + profit);
    }

    @Override
    public boolean equals(Object o) {
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
