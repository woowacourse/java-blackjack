package domain;

public class Card {
    private final Rank rank;
    private final Shape shape;

    public Card(Rank rank, Shape shape) {
        this.rank = rank;
        this.shape = shape;
    }

    int getNumber() {
        return rank.number();
    }
}
