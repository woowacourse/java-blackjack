package domain.player.participant.betresult;

import domain.player.participant.Money;

public class LoseState implements BetResultState {

    @Override
    public Money calculateBetOutcomeOf(final Money money) {
        return money.lose();
    }
}
