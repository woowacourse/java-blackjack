package blackjack.blackjack.state.running;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.finished.BlackjackState;
import blackjack.blackjack.state.finished.BustState;
import blackjack.blackjack.state.finished.StayState;

public final class DealerRunningState extends RunningState {

    private static final int DEALER_THRESHOLD = 16;

    private DealerRunningState(final Hand hand) {
        super(hand);
    }

    public static State initialState(final Hand givenHand) {
        if (givenHand.isBlackjack()) {
            return new BlackjackState(givenHand);
        }
        if (isStay(givenHand)) {
            return new StayState(givenHand);
        }
        return new DealerRunningState(givenHand);
    }

    private static boolean isStay(final Hand givenHand) {
        return givenHand.calculateScore() > DEALER_THRESHOLD;
    }

    @Override
    public State receiveCards(final Hand givenHand) {
        hand.addAll(givenHand);
        if (hand.isBust()) {
            return new BustState(hand);
        }
        if (isStay(hand)) {
            return new StayState(hand);
        }
        return this;
    }
}
