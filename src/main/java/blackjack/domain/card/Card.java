package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getScore() {
        return rank.getScore();
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Card card = (Card) object;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(suit);
        result = 31 * result + Objects.hashCode(rank);
        return result;
    }
}
