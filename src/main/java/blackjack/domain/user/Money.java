package blackjack.domain.user;

import java.util.Objects;

public class Money {
    private final int money;
    public Money(int money) {
        this.money = money;
    }

    public Money() {
        this(0);
    }

    public Money multiply(double rate) {
        return new Money((int) (this.money * rate));
    }

    public int getMoney() {
        return this.money;
    }
}
