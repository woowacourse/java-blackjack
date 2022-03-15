package blackjack.domain.card;

import java.util.*;

public final class Deck {

    private final Stack<Card> deck;

    public Deck(final CardGenerator deckGenerator) {
        deck = deckGenerator.generate();
    }

    public Card draw() {
        return deck.pop();
    }
}
