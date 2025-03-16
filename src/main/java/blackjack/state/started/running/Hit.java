package blackjack.state.started.running;

import blackjack.card.Card;
import blackjack.card.Hand;
import blackjack.state.State;
import blackjack.state.started.finished.Bust;
import blackjack.state.started.finished.Stay;

public class Hit extends Running {
    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State hit(Card card) {
        hand.add(card);
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
