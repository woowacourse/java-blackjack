package domain.card;

public class Card {
    private Shape shape;
    private Number number;

    public Card(Shape shape, Number number) {
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
