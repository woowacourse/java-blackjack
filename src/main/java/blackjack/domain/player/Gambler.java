package blackjack.domain.player;

import blackjack.domain.BetMoney;

public class Gambler extends Player {

    private static final int GAMBLER_GET_CARD_UPPER_BOUND = 21;

    private final BetMoney betMoney;

    public Gambler(final String name, final BetMoney betMoney) {
        super(name);
        this.betMoney = betMoney;
    }

    @Override
    public boolean isNotFinished() {
        return playingCards.isNotFinishedWithBound(GAMBLER_GET_CARD_UPPER_BOUND);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }
}
