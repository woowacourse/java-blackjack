package domain.state.started;

import domain.card.Hand;
import domain.state.State;

public abstract class Started implements State {
    protected final Hand hand;

    public Started(Hand hand) {
        this.hand = hand;
    }

    @Override
    public Hand getHand() {
        return hand;
    }
}
