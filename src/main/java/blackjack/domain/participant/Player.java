package blackjack.domain.participant;

import blackjack.domain.betting.BettingMoney;

public class Player extends Participant {

    private static final int INITIAL_CARD_COUNT = 2;

    private final BettingMoney bettingMoney;
    private boolean isStayed = false;

    public Player(final Name name, final BettingMoney bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    public void stay() {
        isStayed = true;
    }

    public boolean hasAdditionalCard() {
        return getCards().size() > INITIAL_CARD_COUNT;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    @Override
    public boolean canReceiveCard() {
        return !isStayed && !isBust();
    }
}
