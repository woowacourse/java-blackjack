package blackjack.blackjack.state.running;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.finished.Blackjack;
import blackjack.blackjack.state.finished.Bust;

public final class PlayerRunning extends Running {

    private PlayerRunning(final Hand hand) {
        super(hand);
    }

    public static State initialState(final Hand hand) {
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        return new PlayerRunning(hand);
    }

    @Override
    public State receiveCards(final Hand hand) {
        this.hand.addAll(hand);
        if (this.hand.isBust()) {
            return new Bust(this.hand);
        }
        if (this.hand.isBlackjack()) {
            return new Blackjack(this.hand);
        }
        return this;
    }
}
