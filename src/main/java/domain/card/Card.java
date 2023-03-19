package domain.card;

import java.util.Objects;

public final class Card {

    private final Suit suit;
    private final Rank rank;

    private Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card of(final Suit suit, final Rank rank) {
        return new Card(suit, rank);
    }

    public String getSuit() {
        return suit.getName();
    }

    public int getScore() {
        return rank.getScore();
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isAce() {
        return this.rank == Rank.ACE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}
