package model;

public class Card {

    private final CardNumber number;
    private final String shape;

    public Card(final CardNumber number, final String shape) {
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

    public String getShape() {
        return shape;
    }
}
