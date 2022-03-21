package blackjack.model.trumpcard;

import java.util.Objects;

public final class TrumpCard {
    private final TrumpDenomination denomination;
    private final TrumpSuit suit;

    public TrumpCard(TrumpDenomination denomination, TrumpSuit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    int sumDenominationTo(int value) {
        return denomination.sumTo(value);
    }

    boolean isAce() {
        return this.denomination == TrumpDenomination.ACE;
    }

    public TrumpDenomination getDenomination() {
        return denomination;
    }

    public TrumpSuit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrumpCard trumpCard = (TrumpCard) o;
        return denomination == trumpCard.denomination && suit == trumpCard.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, suit);
    }
}
