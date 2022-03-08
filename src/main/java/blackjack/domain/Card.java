package blackjack.domain;

public class Card {
    private final int number;
    private final String shape;

    private Card(final int number, final String shape) {
        this.number = number;
        this.shape = shape;
    }
    public static Card of(final int number, final String shape) {
        return new Card(number, shape);
    }

    public int getNumber() {
        return number;
    }

}