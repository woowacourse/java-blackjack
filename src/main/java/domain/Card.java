package domain;

public class Card {

    private final Shape shape;
    private final Number number;

    private Card(final Shape shape, final Number number) {
        this.shape = shape;
        this.number = number;
    }

    public static Card of(final Shape shape, final Number number) {
        return new Card(shape, number);
    }

    public int getScore() {
        return number.getScore();
    }

    public Number getNumber() {
        return number;
    }
}
