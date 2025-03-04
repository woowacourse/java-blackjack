package domain;

import java.util.List;
import java.util.Objects;

public class Card {

    private final TrumpNumber number;
    private final TrumpShape shape;

    private Card(TrumpNumber number, TrumpShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public static Card of(final TrumpNumber number, final TrumpShape shape) {
        return new Card(number, shape);
    }

    public List<Integer> getScore() {
        return number.getScore();
    }

    public TrumpNumber getNumber() {
        return number;
    }

    public TrumpShape getShape() {
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
        return number == card.number && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, shape);
    }
}
