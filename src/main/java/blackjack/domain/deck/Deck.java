package blackjack.domain.deck;

import java.util.Stack;

import blackjack.domain.card.Card;

public class Deck {

    private final Stack<Card> cards;

    private Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck generateFrom(RandomCardStrategy strategy) {
        return new Deck(strategy.generateDeck());
    }

    public Stack<Card> getAll() {
        return cards;
    }

    public Card draw() {
        return cards.pop();
    }
}
