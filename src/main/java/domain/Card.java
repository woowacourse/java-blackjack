package domain;

public class Card {
    private final Shape shape;
    private final Number number;

    public Card(Shape shape, Number number) {
        this.shape = shape;
        this.number = number;
    }

    public int getScore() {
        return number.getValue();
    }

    public String getNumberDisplayName() {
        return number.getDisplayName();
    }

    public String getShape() {
        return shape.getShape();
    }

    public boolean isAce() {
        return number == Number.ACE;
    }
}
