package blackjack.domain.participant;

import java.util.Objects;

public final class Money {

    private final int money;

    public Money(int money) {
        this.money = money;
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
