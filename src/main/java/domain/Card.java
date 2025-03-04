package domain;

import java.util.Objects;

public class Card {
    private final Rank rank;
    private final Shape shape;

    public Card(Rank rank, Shape shape) {
        this.rank = rank;
        this.shape = shape;
    }

    public int getNumber() {
        return rank.number();
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Card card)) {
            return false;
        }

        return rank == card.rank && shape == card.shape;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(rank);
        result = 31 * result + Objects.hashCode(shape);
        return result;
    }
}
