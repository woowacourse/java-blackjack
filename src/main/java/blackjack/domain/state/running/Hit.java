package blackjack.domain.state.running;

import blackjack.domain.carddeck.Card;
import blackjack.domain.state.hand.Hand;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stay;

public class Hit extends Running {

    public Hit(final Hand hand) {
        super(hand);
    }

    @Override
    public State receiveCard(final Card card) {
        hand.addCard(card);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        return new Hit(hand);
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }
}
