package domain.card;

import java.util.Objects;

public class Card {

    private final Rank rank;
    private final Shape shape;

    public Card(final Rank rank, final Shape shape) {
        this.rank = rank;
        this.shape = shape;
    }

    public boolean isAce() {
        return rank.isAce();
    }

    public int getCardNumber() {
        return rank.getValue();
    }

    public Rank getRank() {
        return rank;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Card card)) {
            return false;
        }
        return Objects.equals(rank, card.rank) && Objects.equals(shape, card.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, shape);
    }
}
