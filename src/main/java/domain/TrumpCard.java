package domain;

import java.util.Objects;

public class TrumpCard {
    private final Rank rank;
    private final Suit suit;

    public TrumpCard(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrumpCard that = (TrumpCard) o;
        return rank == that.rank && suit == that.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}
