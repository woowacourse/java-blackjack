public class Card {

    private final Value value;
    private final Shape shape;

    public Card(final String value, final String shape) {
        this.value = Value.of(value);
        this.shape = Shape.of(shape);
    }
}
