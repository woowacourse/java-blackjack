package blackjack.domain.deck;

import java.util.Stack;

import blackjack.domain.card.Card;

public class Deck {

    private final CardStrategy strategy;
    private final Stack<Card> cards;

    private Deck(CardStrategy strategy, Stack<Card> cards) {
        this.strategy = strategy;
        this.cards = cards;
    }

    public static Deck generateFrom(CardStrategy strategy) {
        return new Deck(strategy, strategy.generateDeck());
    }

    public Card draw() {
        if (cards.isEmpty()) {
            cards.addAll(strategy.generateDeck());
        }
        return cards.pop();
    }
}
