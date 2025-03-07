package model;

import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> deck;

    public Deck(final List<Card> deck) {
        this.deck = deck;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card getCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException();
        }
        return deck.removeLast();
    }

}
