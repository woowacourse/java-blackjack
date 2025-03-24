package blackjack.blackjack.state.running;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.finished.BlackjackState;
import blackjack.blackjack.state.finished.BustState;

public final class PlayerRunningState extends RunningState {

    private PlayerRunningState(final Hand hand) {
        super(hand);
    }

    public static State initialState(final Hand givenHand) {
        if (givenHand.isBlackjack()) {
            return new BlackjackState(givenHand);
        }
        return new PlayerRunningState(givenHand);
    }

    @Override
    public State receiveCards(final Hand givenHand) {
        hand.addAll(givenHand);
        if (hand.isBust()) {
            return new BustState(hand);
        }
        return this;
    }
}
