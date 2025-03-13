package blackjack.model.player;

import blackjack.model.card.BlackJackCards;
import blackjack.model.player.money.Money;

public class Player extends BlackJackPlayer {

    private static final int DRAWABLE_POINT = BLACKJACK_POINT;

    public Player(final String name, final int bettingMoney) {
        super(name, Money.from(bettingMoney));
    }

    @Override
    public BlackJackCards openInitialCards() {
        return blackJackCards;
    }

    @Override
    protected boolean canDrawMoreCard() {
        return getMinimumPoint() < DRAWABLE_POINT;
    }

    public void loseMoney() {
        money.subtract(money.getValue() * 2);
    }

    public void addMoney(final int money) {
        this.money.add(money);
    }
}
