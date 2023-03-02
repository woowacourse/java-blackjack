package domain;

import java.util.Objects;

public class Card {

    private final Value value;
    private final Shape shape;

    public Card(final String value, final String shape) {
        this.value = Value.of(value);
        this.shape = Shape.of(shape);
    }

    public int getScore() {
        return value.getScore();
    }

    public boolean isAce() {
        return value.isAce();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return value == card.value && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, shape);
    }
}
