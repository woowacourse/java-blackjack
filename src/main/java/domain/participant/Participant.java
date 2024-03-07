package domain.participant;

import domain.Deck;

public abstract class Participant {
    protected final Hand hand;

    protected Participant(final Hand hand) {
        this.hand = hand;
    }

    abstract boolean isDrawable();

    abstract void draw(final Deck deck);

    public boolean isDealer() {
        return this instanceof Dealer;
    }

    public HandStatus getHandStatus() {
        return hand.getHandStatus();
    }
}
