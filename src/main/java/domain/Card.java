package domain;

import java.util.Objects;

public class Card {
    private final Rank rank;
    private final Shape shape;


    public Card(final Rank rank, final Shape shape) {
        this.rank = rank;
        this.shape = shape;
    }

    public int getScore() {
        return rank.getScore();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return rank == card.rank && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, shape);
    }
}
