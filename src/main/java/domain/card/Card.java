package domain.card;

import domain.Rank;
import domain.Suit;
import java.util.Objects;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getRankValue() {
        return rank.getScoreValue();
    }

    public int getOneIfAce() {
        if (rank.isAce()) {
            return 1;
        }
        return 0;
    }

    public boolean isAce() {
        return rank.isAce();
    }

    @Override
    public String toString() {
        return rank.getDisplayValue() + suit.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}
