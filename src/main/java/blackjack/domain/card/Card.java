package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Number number;
    private final Suit suit;

    public Card(final Number number, final Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    public Number getDenomination() {
        return number;
    }

    public Suit getSuit() {
        return suit;
    }

    public int toInt() {
        return this.number.getValue();
    }

    public boolean isAce() {
        return number.isAce();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, suit);
    }

    @Override
    public String toString() {
        return "Card{" +
                "denomination=" + number +
                ", suit=" + suit +
                '}';
    }
}
