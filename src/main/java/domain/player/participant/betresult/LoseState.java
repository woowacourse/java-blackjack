package domain.player.participant.betresult;

import domain.player.participant.Money;

public class LoseState implements BetResultState {

    @Override
    public Money calculateBetOutComeOf(final Money money) {
        return money.lose();
    }
}
