package domain;

import domain.constants.Score;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        create();
        Collections.shuffle(cards);
    }

    public Deck(final List<Card> cards) {
        this.cards = cards;
    }

    private void create() {
        for (Shape shape : Shape.values()) {
            addCardsOfShape(shape);
        }
    }

    private void addCardsOfShape(final Shape shape) {
        for (Score score : Score.values()) {
            cards.add(new Card(score, shape));
        }
    }

    public Card pick() {
        return cards.remove(0);
    }

    public int getTotalSize() {
        return cards.size();
    }
}
