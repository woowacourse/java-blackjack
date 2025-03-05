package domain;

import java.util.Objects;

public class Card {

    private final Shape shape;
    private final Rank rank;

    public Card(Shape shape, Rank rank) {
        this.shape = shape;
        this.rank = rank;
    }

    public Shape getShape() {
        return shape;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        return rank.getValue();
    }

    public boolean isAce() {
        return rank == Rank.A;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(shape, card.shape) && Objects.equals(rank, card.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, rank);
    }
}
