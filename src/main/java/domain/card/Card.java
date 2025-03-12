package domain.card;

import static domain.card.Denomination.ACE;

import java.util.Objects;

public class Card {
    private final Denomination denomination;
    private final Suit suit;
    protected int value;

    public Card(final Denomination denomination, final Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
        this.value = denomination.getScore();
    }

    public boolean isAce() {
        return denomination.equals(ACE);
    }

    public void setValueToOne() {
        value = 1;
    }

    public void setValueToZero() {
        value = 0;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Card card)) {
            return false;
        }
        return value == card.value && denomination == card.denomination && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, suit, value);
    }
}
