package blackjack.domain.state;

import blackjack.domain.card.PlayerCards;
import blackjack.domain.player.BetAmount;

public class Bust extends Finished {
    private static final int EARNING_RATE = -1;

    public Bust(PlayerCards cards) {
        super(cards);
    }

    @Override
    protected double earningRate() {
        return EARNING_RATE;
    }

    @Override
    public double profit(State state, BetAmount amount) {
        return amount.getAmount() * earningRate();
    }
}
