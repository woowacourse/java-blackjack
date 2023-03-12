package blackjack.domain.participant;

import java.util.Objects;

public class Money {

    private static final int MIN_BETTING_MONEY = 0;

    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public static Money init() {
        return new Money(MIN_BETTING_MONEY);
    }

    public Money getBlackjackPrize() {
        return new Money((money / 2) + money);
    }

    public Money getBettingPrize() {
        return this;
    }

    public Money loseBettingPrize() {
        return new Money(money * -1);
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
