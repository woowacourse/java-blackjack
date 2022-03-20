package blackjack.domain.card;

import java.util.Objects;

public class PlayingCard {

    private final Suit suit;
    private final Denomination denomination;

    public PlayingCard(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public int getPoint() {
        return denomination.getPoint();
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingCard that = (PlayingCard) o;
        return suit == that.suit && denomination == that.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
