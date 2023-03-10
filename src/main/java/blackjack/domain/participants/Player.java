package blackjack.domain.participants;

import blackjack.dto.HandStatus;

public class Player extends Participant {

    private final BettingMoney bettingMoney;

    public Player(final String name, final BettingMoney bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    @Override
    public boolean isAbleToHit() {
        return isNotBustNorHasMaxScore();
    }

    @Override
    public HandStatus toHandStatus() {
        return new HandStatus(getName(), cards());
    }
}
