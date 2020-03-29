package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards;

    private Deck() {
        this.cards = Card.createCards();
    }

    public static Deck create() {
        Deck deck = new Deck();
        deck.shuffle();
        return deck;
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card pop() {
        if (cards.isEmpty()) {
            this.cards = Card.createCards();
            shuffle();
        }
        return cards.pop();
    }

    public int size() {
        return cards.size();
    }
}
