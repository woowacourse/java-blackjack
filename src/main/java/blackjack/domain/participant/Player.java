package blackjack.domain.participant;

import blackjack.domain.Money;

public class Player extends Participant {

    private final Money money;

    public Player(final String name) {
        super(name);
        this.money = new Money();
    }

    public void initMoney(final int bettingAmount) {
        money.init(bettingAmount);
    }

    public void updateBlackjackPrize() {
        money.multiply();
    }

    public void updatePushPrize() {
        money.toZero();
    }

    public void updateNegativePrize() {
        money.toNegative();
    }

    public int getPrize() {
        return money.getValue();
    }
}
