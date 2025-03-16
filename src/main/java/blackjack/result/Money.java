package blackjack.result;

import java.util.List;
import java.util.Objects;

public class Money {

    private final int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }

    public static Money sumOf(List<Money> moneys) {
        return moneys.stream()
                .reduce(Money.from(0), Money::plus);
    }

    public Money plus(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money applyProfitRate(int profitPercent) {
        int percent = 100;
        return Money.from(amount * profitPercent / percent);
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
