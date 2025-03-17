package domain.state;

import domain.gamer.Hand;

public abstract class Started implements State {

    private final Hand hand;

    public Started(final Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public int compareTo(final State other) {
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
