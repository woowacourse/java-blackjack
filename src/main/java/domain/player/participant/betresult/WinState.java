package domain.player.participant.betresult;

import domain.player.participant.Money;

public class WinState implements BetResultState {

    private static final double WINNER_PERCENT = 1.5;

    @Override
    public Money calculateBetOutComeOf(final Money money) {
        return money.times(WINNER_PERCENT);
    }
}
