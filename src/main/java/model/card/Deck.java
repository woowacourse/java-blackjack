package model.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private final Stack<Card> cards;

    public Deck() {
        this.cards = createCards();
    }

    private Stack<Card> createCards() {
        Stack<Card> cards = new Stack<>();

        for (Shape shape : Shape.values()) {
            addCard(cards, shape);
        }
        Collections.shuffle(cards);

        return cards;
    }

    private static void addCard(final Stack<Card> cards, final Shape shape) {
        for (Value value : Value.values()) {
            cards.push(new Card(shape, value));
        }
    }

    public Card pick() {
        return cards.pop();
    }
}
