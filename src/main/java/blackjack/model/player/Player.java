package blackjack.model.player;

import blackjack.model.card.BlackJackCards;
import blackjack.model.player.betting.Betting;

public class Player extends BlackJackPlayer {

    private static final int DRAWABLE_POINT = BLACKJACK_POINT;

    private final Betting betting;

    public Player(final String name, final int bettingMoney) {
        super(name);
        betting = Betting.bet(bettingMoney);
    }

    public int getBettingMoney() {
        return betting.getBettingMoney();
    }

    public void earnMoney(final int money) {
        betting.earn(money);
    }

    public void loseMoney() {
        betting.lose();
    }

    public int getProfit() {
        return betting.getProfit();
    }

    @Override
    public BlackJackCards openInitialCards() {
        return blackJackCards;
    }

    @Override
    protected boolean canDrawMoreCard() {
        return getMinimumPoint() < DRAWABLE_POINT;
    }
}
