package blackjack.domain.card;

import java.util.Objects;
import java.util.StringJoiner;

public class Card {

    private final Value value;
    private final Shape shape;

    public Card(Value value, Shape shape) {
        this.value = Objects.requireNonNull(value);
        this.shape = Objects.requireNonNull(shape);
    }

    public int getMinScore() {
        return value.getMinScore();
    }

    public int getMaxScore() {
        return value.getMaxScore();
    }

    public boolean isAce() {
        return value.isAce();
    }

    public Value getValue() {
        return value;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Card card = (Card) object;
        return value == card.value && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, shape);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Card.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .add("shape=" + shape)
                .toString();
    }
}
