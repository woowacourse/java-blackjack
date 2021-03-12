package blackjack.domain.state;

import blackjack.domain.card.Hand;
import blackjack.domain.player.BetAmount;

public class Blackjack extends Finished {
    private static final double EARNING_RATE = 1.5;

    public Blackjack(Hand cards) {
        super(cards);
    }

    @Override
    public double profit(State state, BetAmount amount) {
        if (state.cards().isBlackjack()) {
            return DRAW_AMOUNT;
        }
        return amount.getAmount() * EARNING_RATE;
    }
}
