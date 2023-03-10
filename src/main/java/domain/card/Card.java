package domain.card;

import java.util.Objects;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    private Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Card of(final Suit suit, final Denomination denomination) {
        return new Card(suit, denomination);
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && denomination == card.denomination;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", denomination=" + denomination +
                '}';
    }
}
