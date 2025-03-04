package policy;

public class Card {
    Shape shape;
    String number;

    public Card(String shape, String number) {
        this.shape = Shape.check(shape);
        this.number = number;
    }
}
