package domain.player.participant.betresult;

import domain.player.participant.Money;

public class TieState implements BetResultState {

    @Override
    public Money calculateBetOutcomeOf(final Money money) {
        return money;
    }
}
