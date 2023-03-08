package domain.card;

import java.util.Objects;

public final class Card {
    private final Shape shape;
    private final Value value;

    public Card(final Value value, final Shape shape) {
        this.shape = shape;
        this.value = value;
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

    public int getDefaultScore() {
        return value.getDefaultScore();
    }

    public Value getValue() {
        return value;
    }

    public Shape getShape() {
        return shape;
    }
}
