package blackjack.domain.state;

import blackjack.domain.card.Hand;

public class Blackjack extends Finished {

    protected Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate(State state) {
        if (state.hand().isBlackjack()) {
            return BLACKJACK_TIE_RATE;
        }
        return BLACKJACK_WIN_RATE;
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
