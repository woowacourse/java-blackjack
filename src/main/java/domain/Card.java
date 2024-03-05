package domain;

import domain.constants.CardValue;
import domain.constants.Shape;

public class Card {
    private final CardValue cardValue;
    private final Shape shape;

    public Card(CardValue cardValue, Shape shape) {
        this.cardValue = cardValue;
        this.shape = shape;
    }

    public int getValue() {
        return cardValue.getValue();
    }

    public String getName() {
        return cardValue.getName();
    }

    public String getShape() {
        return shape.getName();
    }
}
