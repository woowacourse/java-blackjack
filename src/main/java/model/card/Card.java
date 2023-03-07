package model.card;

import java.util.Objects;

public class Card {

    private final Shape shape;
    private final Value value;

    public Card(final Shape shape, final Value value) {
        this.shape = shape;
        this.value = value;
    }

    public boolean isAce() {
        return value == Value.ACE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Card card = (Card) o;
        return shape == card.shape && value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, value);
    }

    @Override
    public String toString() {
        return value.getName() + shape.getShape();
    }

    public int getValue() {
        return this.value.getValue();
    }

    public String getShape() {
        return this.shape.getShape();
    }

    public String getName() {
        return this.value.getName();
    }
}
