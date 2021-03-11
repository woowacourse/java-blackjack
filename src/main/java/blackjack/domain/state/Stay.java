package blackjack.domain.state;

import blackjack.domain.card.PlayerCards;
import blackjack.domain.player.BetAmount;

public class Stay extends Finished {
    private static final int EARNING_RATE = 1;

    public Stay(PlayerCards cards) {
        super(cards);
    }

    @Override
    protected double earningRate() {
        return EARNING_RATE;
    }

    @Override
    public double profit(State state, BetAmount amount) {
        if (state.isBust() || state.score() < score()) {
            return amount.getAmount() * earningRate();
        }
        if (state.isBlackjack() || state.score() > score()) {
            return -amount.getAmount() * earningRate();
        }
        return DRAW_AMOUNT;
    }
}
