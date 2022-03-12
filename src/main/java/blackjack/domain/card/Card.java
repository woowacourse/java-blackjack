package blackjack.domain.card;

import java.util.Objects;

public class Card {

    public static final int START_CARD_COUNT = 2;

    private final Suit suit;
    private final Denomination denomination;

    public Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
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
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }
}
