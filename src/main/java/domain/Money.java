package domain;

import java.util.Objects;

public class Money {

    public static final int DEFAULT_AMOUNT = 0;

    private int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money of(int amount) {
        return new Money(amount);
    }

    public void increase(int increaseAmount) {
        amount += increaseAmount;
    }

    public void decrease(int decreaseAmount) {
        amount -= decreaseAmount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Money money = (Money) object;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
