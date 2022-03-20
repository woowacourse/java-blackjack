package blackjack.domain.state;

import blackjack.domain.card.Hand;

public abstract class Started implements State {

    protected final Hand hand;

    protected Started(Hand hand) {
        this.hand = hand;
    }

    @Override
    public final Hand hand() {
        return hand;
    }
}
