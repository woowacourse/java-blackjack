package blackjack.blackjack.state.running;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.finished.Blackjack;
import blackjack.blackjack.state.finished.Bust;
import blackjack.blackjack.state.State;

public final class PlayerRunning extends Running implements State {

    private PlayerRunning(final Hand cards) {
        super(cards);
    }

    public static State initialState(final Hand hand) {
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        return new PlayerRunning(hand);
    }

    @Override
    public State receiveCards(final Hand hand) {
        cards.addAll(hand);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return this;
    }
}
