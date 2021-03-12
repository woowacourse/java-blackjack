package blackjack.domain.state;

import blackjack.domain.card.Hand;
import blackjack.domain.player.BetAmount;

public class Stay extends Finished {
    private static final int EARNING_RATE = 1;

    public Stay(Hand cards) {
        super(cards);
    }

    @Override
    public double profit(State state, BetAmount amount) {
        if (state.isBust() || state.score() < score()) {
            return amount.getAmount() * EARNING_RATE;
        }
        if (state.isBlackjack() || state.score() > score()) {
            return -amount.getAmount() * EARNING_RATE;
        }
        return DRAW_AMOUNT;
    }
}
