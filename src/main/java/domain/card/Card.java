package domain.card;

import java.util.Objects;

public class Card {
    private final Rank rank;
    private final Shape shape;

    public Card(final Rank rank, final Shape shape) {
        this.rank = rank;
        this.shape = shape;
    }

    public int getCardNumber() {
        return rank.getValue();
    }

    public boolean isAce() {
        return rank.isAce();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
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

    @Override
    public String toString() {
        return rank.getName() + shape.getName();
    }
}
