package blackjack.domain;

import java.util.Objects;

public class Card {
    private Suit suit;
    private Rank rank;

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
