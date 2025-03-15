package domain.card;

import java.util.Objects;

public class Card {
    private final Shape shape;
    private final Number number;

    public Card(final Shape shape, final Number number) {
        this.shape = shape;
        this.number = number;
    }

    public boolean isA() {
        return number.isA();
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
        Card that = (Card) o;
        return (Objects.equals(shape, that.shape) && Objects.equals(number, that.number));
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, number);
    }
}
