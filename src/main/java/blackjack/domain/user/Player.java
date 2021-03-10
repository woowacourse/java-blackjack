package blackjack.domain.user;

import blackjack.domain.Money;

public class Player extends User {

    private final Money money;

    public Player(String name, int money) {
        this(name, new Money(money));
    }

    public Player(String name, Money money) {
        super(name);
        this.money = money;
    }

    public boolean canHit() {
        return getScore().isNotBust() && !getScore().isBlackJack();
    }

    public Money getMoney() {
        return money;
    }
}
