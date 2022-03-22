package blackjack.domain.state;

import blackjack.domain.card.Hand;

public class Stay extends Finished {

    protected Stay(Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate(State state) {
        Hand otherHand = state.hand();

        if (otherHand.isBust() || hand.compareScore(otherHand)) {
            return WIN_RATE;
        }
        if (hand.getScore() == otherHand.getScore()) {
            return TIE_RATE;
        }
        return LOSE_RATE;
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
