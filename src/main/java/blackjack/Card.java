package blackjack;

public class Card {
    private final Number number;
    private final Shape shape;

    public Card(Number number, Shape shape) {
        this.number = number;
        this.shape = shape;
    }

    public int getValue() {
        return number.getValue();
    }
}
