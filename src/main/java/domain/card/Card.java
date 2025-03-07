package domain.card;

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
}
