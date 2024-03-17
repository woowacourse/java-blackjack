package blackjack.domain.money;

import java.util.Objects;

public class Money {

    public static final Money ZERO = new Money(0);

    private final int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public Money subtract(Money prize) {
        return new Money(amount - prize.getAmount());
    }

    public Money multiply(double multipleAmount) {
        return new Money((int) (amount * multipleAmount));
    }

    public Money negate() {
        return multiply(-1);
    }

    public int getAmount() {
        return amount;
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
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
