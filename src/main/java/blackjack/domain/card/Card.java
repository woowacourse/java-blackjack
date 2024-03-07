package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Value value;
    private final Shape shape;

    public Card(Value value, Shape shape) {
        this.value = value;
        this.shape = shape;
    }

    public int getMinScore() {
        return value.getMinScore();
    }

    public int getMaxScore() {
        return value.getMaxScore();
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

    public Value getValue() {
        return value;
    }

    public Shape getShape() {
        return shape;
    }
}
