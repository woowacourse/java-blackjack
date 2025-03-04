package domain;

import java.util.ArrayDeque;
import java.util.Queue;

public class Deck {
    private final Queue<Card> deck;

    public Deck(final Queue<Card> deck) {
        this.deck = new ArrayDeque<>(deck);
    }

    public Card pickCard() {
        return deck.poll();
    }
}
