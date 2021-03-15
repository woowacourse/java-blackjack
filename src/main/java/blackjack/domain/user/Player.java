package blackjack.domain.user;

import blackjack.domain.Money;

public class Player extends User {
    private final Money bettingMoney;

    public Player(Name name, Money money) {
        super(name);
        this.bettingMoney = money;
    }

    @Override
    public boolean canContinue() {
        return !this.isBust() && !this.isBlackJack();
    }

    public Money getBettingMoney() {
        return this.bettingMoney;
    }
}
