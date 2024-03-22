package domain.result;

import java.util.Objects;

import domain.participant.attributes.Money;

public class Profit implements Money {

    private static final int DEFAULT_AMOUNT = 0;

    private final int amount;

    private Profit(final int amount) {
        this.amount = amount;
    }

    public static Profit of(final Money seed, final ProfitRate profitRate) {
        return new Profit((int) (seed.amount() * profitRate.getValue()));
    }

    public static Profit defaultAmount() {
        return new Profit(DEFAULT_AMOUNT);
    }

    public Profit subtract(final Money money) {
        return new Profit(this.amount - money.amount());
    }

    @Override
    public int amount() {
        return amount;
    }

    @Override
    public boolean equals(final Object object) {
        return (object instanceof Profit other) && (amount == other.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Profit{" +
                "amount=" + amount +
                '}';
    }
}
