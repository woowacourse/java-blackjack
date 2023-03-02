package domain;

import java.util.Objects;

public class Card {

    private final Shape shape;
    private final Value value;

    public Card(Shape shape, Value value) {
        this.shape = shape;
        this.value = value;
    }

    public Shape getShape() {
        return shape;
    }

    public Value getValue() {
        return value;
    }

    public boolean isAce() {
        return value == Value.ACE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return shape == card.shape && value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, value);
    }

    @Override
    public String toString() {
        return "Card{" +
                "shape=" + shape +
                ", value=" + value +
                '}';
    }

}
