package domain;

public class Card {

    private final Shape shape;
    private final Number number;

    public Card(Shape shape, Number number) {
        this.shape = shape;
        this.number = number;
    }

    public boolean isAce() {
        return this.number == Number.A;
    }

    public int getScore() {
        return number.getScore();
    }

    public String getShapeName() {
        return shape.getName();
    }
    public String getNumberName() {
        return number.getName();
    }

}
