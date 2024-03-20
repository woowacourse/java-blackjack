package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Score getScore() {
        return denomination.getValue();
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card card)) {
            return false;
        }
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
