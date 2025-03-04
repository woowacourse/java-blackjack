package domain;

public class Card {
    private final Rank rank;
    private final Shape shape;


    public Card(final Rank rank, final Shape shape) {
        this.rank = rank;
        this.shape = shape;
    }
}
