package blackjack;

import static blackjack.Rank.ACE;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int hardRank() {
        return rank.hard();
    }

    public int softRank() {
        return rank.soft();
    }

    public boolean isAce() {
        return rank == ACE;
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank=" + rank +
                ", suit=" + suit +
                '}';
    }
}
