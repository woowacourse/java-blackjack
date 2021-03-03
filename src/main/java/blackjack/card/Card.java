package blackjack.card;

public class Card {
    private final Number number;
    private final Shape shape;

    public Card(Number number, Shape shape) {
        this.number = number;
        this.shape = shape;
    }

    public boolean isAce() {
        return Number.ACE.equals(number);
    }

    public int getValue() {
        return number.getValue();
    }
}
