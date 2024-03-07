package domain;

import java.util.Objects;

public class Card {

    private final Shape shape;
    private final Rank rank;

    public Card(final Shape shape, final Rank rank) {
        this.shape = shape;
        this.rank = rank;
    }

    public int getScore() {
        return rank.getScore();
    }

    public Shape getShape() {
        return shape;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return shape == card.shape && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, rank);
    }
}
