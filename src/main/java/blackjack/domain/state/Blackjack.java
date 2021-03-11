package blackjack.domain.state;

import blackjack.domain.card.PlayerCards;
import blackjack.domain.player.BetAmount;

public class Blackjack extends Finished {
    private static final double EARNING_RATE = 1.5;

    public Blackjack(PlayerCards cards) {
        super(cards);
    }

    @Override
    protected double earningRate() {
        return EARNING_RATE;
    }

    @Override
    public double profit(State state, BetAmount amount) {
        if (state.cards().isBlackjack()) {
            return DRAW_AMOUNT;
        }
        return amount.getAmount() * earningRate();
    }
}
