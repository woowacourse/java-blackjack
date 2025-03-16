package domain.state.started.running;

import domain.card.Card;
import domain.card.Hand;
import domain.state.State;
import domain.state.started.finished.Bust;
import domain.state.started.finished.Stay;

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
