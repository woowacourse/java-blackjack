package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public boolean isAceCard() {
        return denomination == Denomination.ACE;
    }

    public String getSuit() {
        return suit.getValue();
    }

    public String getDenominationType() {
        return denomination.getType();
    }

    public int getDenomination() {
        return denomination.getValue();
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
        return denomination == card.denomination && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, suit);
    }
}
