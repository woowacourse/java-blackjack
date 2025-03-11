package model.card;

import java.util.Objects;

public class Card {

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public boolean matchesRank(Rank rank) {
        return this.rank.matches(rank);
    }

    public int getScore() {
        return rank.getScore();
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && Objects.equals(rank, card.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}
