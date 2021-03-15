package blackjack.domain.card;

import java.util.Objects;

public final class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public String getRankInitial() {
        return denomination.getInitial();
    }

    public int getRankValue() {
        return denomination.getValue();
    }

    public int getUpperValue() {
        if (isAce()) {
            return Denomination.ACE_UPPER_VALUE;
        }
        return denomination.getValue();
    }

    public String getSuitName() {
        return suit.getName();
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
}
