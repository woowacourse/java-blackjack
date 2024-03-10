package blackjack.domain.card;

import java.util.Stack;

public class Deck {

    private final Stack<Card> deck;

    public Deck(final Stack<Card> deck) {
        this.deck = deck;
    }

    public Card pop() {
        return deck.pop();
    }
}
