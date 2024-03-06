package blackjack.model;

public class Card {
    private final Shape shape;
    private final Score score;

    public Card(final Shape shape, final Score score) {
        this.shape = shape;
        this.score = score;
    }
}
