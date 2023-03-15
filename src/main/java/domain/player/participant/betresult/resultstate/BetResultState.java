package domain.player.participant.betresult.resultstate;

import domain.player.participant.Money;

@FunctionalInterface
public interface BetResultState {

    Money calculateBetOutcomeOf(final Money money);
}
