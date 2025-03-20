package blackjack.domain.card;

import java.util.Objects;

public final class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return this.denomination == Denomination.A;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Card card)) {
            return false;
        }
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }

    public Suit getSuit() {
        return suit;
    }

    public String getDenominationName() {
        return denomination.getName();
    }

    public int getCardMinNumber() {
        return denomination.getMinNumber();
    }

    public int getCardMaxNumber() {
        return denomination.getMaxNumber();
    }
}
