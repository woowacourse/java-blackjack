package card;

import java.util.Objects;

public class Card {

    private final Shape shape;
    private final Number number;

    public Card(Shape shape, Number number) {
        this.shape = shape;
        this.number = number;
    }

    public boolean isAce() {
        return this.number == Number.ACE;
    }

    public int getScore() {
        return number.getScore();
    }

    public Shape getShape() {
        return shape;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return shape == card.shape && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, number);
    }
}
