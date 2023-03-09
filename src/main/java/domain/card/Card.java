package domain.card;

import java.util.Objects;

public class Card {

    private final Value value;
    private final Shape shape;

    public Card(final Value value, final Shape shape) {
        this.value = value;
        this.shape = shape;
    }

    public int getScore(final int totalScore) {
        return value.getScore(totalScore);
    }

    public String getValue(){
        return value.getValue();
    }

    public String getShape(){
        return shape.getShape();
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
