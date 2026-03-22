package domain.rule;

import domain.card.Hand;

public class Blackjack extends Finished {
    public static final double BLACKJACK_EARNING_RATE = 1.5;

    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate(State dealerState) {
        if (dealerState.isBlackjack()) {
            return 0;
        }
        return BLACKJACK_EARNING_RATE;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }
}
