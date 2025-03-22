package blackjack.state.started;

import blackjack.card.Hand;
import blackjack.state.State;

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
