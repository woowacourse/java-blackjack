package domain.participant;

import domain.Deck;

public abstract class Participant {
    protected final Hand hand;

    protected Participant(final Hand hand) {
        this.hand = hand;
    }

    abstract public boolean isDrawable();

    abstract public void draw(final Deck deck);

    public boolean isDealer() {
        return this instanceof Dealer;
    }

    public HandStatus getHandStatus() {
        return hand.getHandStatus();
    }
}
