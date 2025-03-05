package domain;

import java.util.Objects;

public class Card {
    private final Rank rank;
    private final Suit suit;

    protected Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int score() {
        return rank.getValue();
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(rank);
        result = 31 * result + Objects.hashCode(suit);
        return result;
    }
}
