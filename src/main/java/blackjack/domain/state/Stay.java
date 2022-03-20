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
            return 1.0;
        }
        if (hand.getScore() == otherHand.getScore()) {
            return 0;
        }
        return -1.0;
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
