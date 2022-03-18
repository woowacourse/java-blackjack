package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Denomination denomination;
    private final Suit suit;

    public Card(final Denomination denomination, final Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public boolean isAceCard() {
        return suit == Suit.ACE;
    }

    public String getDenomination() {
        return denomination.getValue();
    }

    public String getSuitType() {
        return suit.getType();
    }

    public int getSuit() {
        return suit.getValue();
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
