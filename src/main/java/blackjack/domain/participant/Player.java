package blackjack.domain.participant;

import blackjack.domain.betting.BettingMoney;

public class Player extends Participant {

    private final BettingMoney bettingMoney;
    private boolean isStayed = false;

    public Player(final String name, final BettingMoney bettingMoney) {
        super(new Name(name));
        this.bettingMoney = bettingMoney;
    }

    public void stay() {
        isStayed = true;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    @Override
    public boolean canReceiveCard() {
        return !isStayed && !isBust();
    }
}
