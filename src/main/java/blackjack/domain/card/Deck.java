package blackjack.domain.card;

import java.util.Stack;

public class Deck {

    private final Stack<Card> deck;

    public Deck(final DeckFactory deckFactory) {
        this.deck = deckFactory.generate();
    }

    public Card pop() {
        return deck.pop();
    }
}
