package model;

public class Card {

    private final CardNumber number;
    private final Shape shape;

    public Card(final CardNumber number, final Shape shape) {
        this.number = number;
        this.shape = shape;
    }

    public CardNumber getNumber() {
        return number;
    }

    public int getNumberValue() {
        return number.getNumber();
    }

    public boolean isSameNumber(final CardNumber number) {
        return this.number == number;
    }

    public Shape getShape() {
        return shape;
    }
}
