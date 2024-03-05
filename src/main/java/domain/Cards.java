package domain;

import domain.constants.CardValue;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards = new ArrayList<>();

    public Cards() {
        create();
    }

    private void create() {
        for (Shape shape : Shape.values()) {
            addCardsOfShape(shape);
        }
    }

    private void addCardsOfShape(final Shape shape) {
        for (CardValue cardValue : CardValue.values()) {
            cards.add(new Card(cardValue, shape));
        }
    }

    public int getTotalSize() {
        return cards.size();
    }
}
