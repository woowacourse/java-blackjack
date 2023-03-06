package model.card;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final Deque<Card> cards;

    public Deck() {
        this.cards = createCards();
    }

    private Deque<Card> createCards() {
        Deque<Card> cards = new LinkedList<>();

        for (Shape shape : Shape.values()) {
            addCard(cards, shape);
        }
        Collections.shuffle((List<?>) cards);

        return cards;
    }

    private static void addCard(final Deque<Card> cards, final Shape shape) {
        for (Value value : Value.values()) {
            cards.push(new Card(shape, value));
        }
    }

    public Card pick() {
        return cards.pop();
    }
}
