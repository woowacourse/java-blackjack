package domain.participant;

import domain.Deck;
import domain.PlayingCard;

import java.util.List;

public abstract class Participant {
    protected final Hand hand;

    protected Participant(final Hand hand) {
        this.hand = hand;
    }

    abstract public boolean isDrawable();

    public void draw(final Deck deck) {
        hand.addCard(deck.drawn());
    }

    public int getHandSum() {
        return hand.getCardsNumberSum();
    }

    public List<PlayingCard> getHandCards() {
        return hand.getPlayingCards();
    }
}
