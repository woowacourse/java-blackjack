package domain.card;

import java.util.Objects;

public final class Card {
    private static final int LOW_ACE_VALUE = 1;

    private final Suit suit;
    private final Rank rank;

    private Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card from(final Suit suit, final Rank rank) {
        return new Card(suit, rank);
    }

    public boolean isAce() {
        return rank.getValue() == LOW_ACE_VALUE;
    }

    public int fetchValue() {
        return rank.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card)o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }

    @Override
    public String toString() {
        return rank.getName() + suit.getShape();
    }
}
