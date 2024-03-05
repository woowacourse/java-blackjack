package domain;

import domain.constants.CardValue;
import domain.constants.Shape;
import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return cardValue == card.cardValue && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardValue, shape);
    }
}
