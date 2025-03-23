package blackjack.blackjack.state.running;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.finished.Blackjack;
import blackjack.blackjack.state.finished.Bust;
import blackjack.blackjack.state.finished.Stay;

public final class DealerRunning extends Running {

    private static final int DEALER_THRESHOLD = 16;

    private DealerRunning(final Hand hand) {
        super(hand);
    }

    public static State initialState(final Hand givenHand) {
        if (givenHand.isBlackjack()) {
            return new Blackjack(givenHand);
        }
        if (isStay(givenHand)) {
            return new Stay(givenHand);
        }
        return new DealerRunning(givenHand);
    }

    private static boolean isStay(final Hand givenHand) {
        return givenHand.calculateScore() > DEALER_THRESHOLD;
    }

    @Override
    public State receiveCards(final Hand givenHand) {
        hand.addAll(givenHand);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        if (isStay(hand)) {
            return new Stay(hand);
        }
        return this;
    }
}
