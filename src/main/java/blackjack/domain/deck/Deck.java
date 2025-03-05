package blackjack.domain.deck;

import java.util.Stack;

import blackjack.domain.card.Card;

public class Deck {

    private final Stack<Card> cards;

    public Deck(RandomCardStrategy strategy) {
        this.cards = strategy.generateDeck();
    }

    public Stack<Card> getAll() {
        return cards;
    }

    public Card draw() {
        return cards.pop();
    }
}
