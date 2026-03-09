package blackjack.domain;

import java.util.Objects;

public class Card {

    private final Rank rank;
    private final Shape shape;

    public Card(Rank rank, Shape shape) {
        this.rank = rank;
        this.shape = shape;
    }

    public int getScore() {
        return rank.getScore();
    }

    public String getCardName() {
        return rank.getName() + shape.getName();
    }

    public boolean isAce() {
        return rank.isAce();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, shape);
    }
}
