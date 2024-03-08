package domain.participant;

import domain.playingcard.Deck;
import domain.playingcard.PlayingCard;

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

    public boolean isDealer() {
        return this instanceof Dealer;
    }

    public int getHandSum() {
        return hand.getCardsNumberSum();
    }

    public List<PlayingCard> getHandCards() {
        return hand.getPlayingCards();
    }

    public boolean isBust() {
        return hand.isBust();
    }
}
