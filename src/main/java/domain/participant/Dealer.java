package domain.participant;

import domain.card.Card;
import domain.card.Deck;

public class Dealer extends Player {

    public static final String DEALER_NAME = "딜러";
    public static final int THRESHOLD = 16;

    private final Deck deck;

    public Dealer() {
        super(DEALER_NAME);
        this.deck = new Deck();
    }

    public void shuffleDeck() {
        deck.shuffle();
    }

    public Card drawCard() {
        return deck.draw();
    }

    public boolean isBelowThreshold() {
        return getHandTotal() <= THRESHOLD;
    }

    public Card openOneCard() {
        return hand.getFirstCard();
    }

    public int getCardCount() {
        return hand.getSize();
    }

    public int getExtraHandSize() {
        return hand.getExtraSize();
    }
}
