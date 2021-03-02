package blackjack.domain;

public class Card {
    private final Shape shape;
    private final Value value;

    public Card(Shape shape, Value value) {
        this.shape = shape;
        this.value = value;
    }
}
