package blackjack.domain.card;

import java.util.*;

public class Deck {

    private final Stack<Card> deck;

    public Deck(DeckGenerator deckGenerator) {
        deck = deckGenerator.generate();
    }

    public Card draw() {
        return deck.pop();
    }
}
