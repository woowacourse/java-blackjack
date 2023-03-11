package domain.card;

import java.util.Objects;

public class Card {
    private static final int HIGH_ACE_VALUE = 11;
    private final Suit suit;
    private final Rank rank;

    private Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card from(Suit suit, Rank rank) {
        return new Card(suit, rank);
    }

    public boolean isAce() {
        return rank.getValue() == HIGH_ACE_VALUE;
    }

    public int fetchValue() {
        return rank.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card)o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }

    @Override
    public String toString() {
        return rank.getName() + suit.getShape();
    }

}

