package domain.card;

import domain.game.Score;
import java.util.Objects;

public class Card {
    private final Rank rank;
    private final Suit suit;

    protected Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    public Score getScore() {
        return rank.getScore();
    }

    public String getNotation() {
        return rank.getName() + suit.getName();
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
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
