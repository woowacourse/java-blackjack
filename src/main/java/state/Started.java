package state;

import card.CardHand;
import card.GameScore;

public abstract class Started implements State {
    protected final CardHand cardHand;

    public Started(final CardHand cardHand) {
        this.cardHand = cardHand;
    }

    public CardHand cardHand() {
        return this.cardHand;
    }

    @Override
    public GameScore calculateScore() {
        return cardHand.calculateScore();
    }
}
