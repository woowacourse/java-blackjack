package domain.state;

import domain.gamer.Hand;

public abstract class StartedState implements State {

    private final Hand hand;

    public StartedState(final Hand hand) {
        this.hand = hand;
    }

    public final Hand getHand() {
        return hand;
    }

    @Override
    public final int compareTo(final State other) {
        final Hand otherHand = other.getHand();
        final int otherSum = otherHand.calculateSumOfRank();

        if (otherHand.isBust() && hand.isBust() || hand.calculateSumOfRank() == otherSum) {
            return 0;
        }
        if (otherHand.isBust() || (!hand.isBust() && hand.calculateSumOfRank() > otherSum)) {
            return 1;
        }
        return -1;
    }
}
