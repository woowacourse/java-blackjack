package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Rank rank;
    private final Suit suit;

    public Card(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isAce() {
        return this.rank == Rank.ACE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }
}
