package blackjack.domain.card;

import java.util.Objects;

public final class Card {

    private final CardShape shape;
    private final CardValue value;

    public Card(CardShape shape, CardValue value) {
        this.shape = shape;
        this.value = value;
    }

    public String getShape() {
        return shape.getDescription();
    }

    public CardValue getValue() {
        return value;
    }

    public int getPoint() {
        return value.getPoint();
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
        return shape == card.shape && value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, value);
    }
}
