package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public int getScore() {
        return denomination.getScore();
    }

    public boolean isAce() {
        return this.denomination.equals(Denomination.ACE);
    }

    public String getSuitName() {
        return this.suit.getName();
    }

    public String getDenominationName() {
        return this.denomination.getName();
    }

    @Override
    public String toString() {
        return "Card@" +
                "suit: " + this.suit.getName() +
                ", denomination: " + this.denomination.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
