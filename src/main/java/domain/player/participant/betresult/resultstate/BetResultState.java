package domain.player.participant.betresult.resultstate;

import domain.player.participant.Money;

public interface BetResultState {

    BetResultState NULL = money -> Money.MIN;

    Money calculateBetOutcomeOf(final Money money);
}
