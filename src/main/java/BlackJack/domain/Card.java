package BlackJack.domain;

public class Card {

    private Shape shape;
    private Number number;

    public Card(Shape shape, Number number) {
        this.shape = shape;
        this.number = number;
    }

    public Shape getShape() {
        return shape;
    }

    public Number getNumber() {
        return number;
    }

}
