package domain.player.participant.betresult;

import domain.player.participant.Money;

public interface BetResultState {

    Money calculateBetOutcomeOf(final Money money);

    BetResultState NULL = money -> Money.MIN;
}
