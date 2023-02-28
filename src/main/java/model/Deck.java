package model;

import model.card.Card;
import model.card.Shape;
import model.card.Value;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        this.cards = createCards();
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();

        for (Shape shape : Shape.values()) {
            addCard(cards, shape);
        }

        return cards;
    }

    private static void addCard(final List<Card> cards, final Shape shape) {
        for (Value value : Value.values()) {
            cards.add(new Card(shape, value));
        }
    }
}
