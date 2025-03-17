package domain.participant;

import java.util.Objects;

public class Money {

    public static final int DEFAULT_AMOUNT = 0;
    public static final double BLACKJACK_BET_RATIO = 1.5;

    private int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money of(int amount) {
        return new Money(amount);
    }

    public void increase(int increaseAmount) {
        this.amount += increaseAmount;
    }

    public void decrease(int decreaseAmount) {
        this.amount -= decreaseAmount;
    }

    public int getAmount() {
        return amount;
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
