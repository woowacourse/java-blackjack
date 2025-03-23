package blackjack.blackjack.state.running;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.finished.Blackjack;
import blackjack.blackjack.state.finished.Bust;

public final class PlayerRunning extends Running {

    private PlayerRunning(final Hand hand) {
        super(hand);
    }

    public static State initialState(final Hand givenHand) {
        if (givenHand.isBlackjack()) {
            return new Blackjack(givenHand);
        }
        return new PlayerRunning(givenHand);
    }

    @Override
    public State receiveCards(final Hand givenHand) {
        hand.addAll(givenHand);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        return this;
    }
}
