package model.state;

import model.card.CardHand;

public abstract class Started implements State {
    protected final CardHand cardHand;

    public Started(final CardHand cardHand) {
        this.cardHand = cardHand;
    }

    public CardHand cardHand() {
        return this.cardHand;
    }
}
