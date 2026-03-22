package domain.rule;

import domain.card.Hand;

public class Stay extends Finished {
    private static final double WIN_EARNING_RATE = 1.0;
    private static final double LOSE_EARNING_RATE = -1.0;

    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate(State dealerState) {
        if (dealerState.isBust()) {
            return WIN_EARNING_RATE;
        }
        if (dealerState.isBlackjack()) {
            return LOSE_EARNING_RATE;
        }
        if (hand.calculateScore() > dealerState.cards().calculateScore()) {
            return WIN_EARNING_RATE;
        }
        if (hand.calculateScore() < dealerState.cards().calculateScore()) {
            return LOSE_EARNING_RATE;
        }
        return 0;
    }
}
