package domain.state;

import domain.deck.Card;
import domain.gamer.Hand;

public abstract class Finished extends Started {

    public Finished(final Hand hand) {
        super(hand);
    }

    @Override
    public State hit(final Card card) {
        return null;
    }

    @Override
    public State stay() {
        return null;
    }
}
