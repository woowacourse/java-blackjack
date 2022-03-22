package blackjack.domain.money;

import java.util.Objects;

import blackjack.utils.IntegerUtils;

public class Money {

    private final int amount;

    public Money(String input) {
        this(IntegerUtils.parseInt(input));
    }

    public Money(int amount) {
        this.amount = amount;
    }

    public Money multiply(double rate) {
        return new Money((int)(this.amount * rate));
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Money money = (Money)o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Money{" +
            "amount=" + amount +
            '}';
    }
}
