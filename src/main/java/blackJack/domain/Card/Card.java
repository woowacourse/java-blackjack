package blackJack.domain.Card;

import java.util.Objects;

public class Card {
    private Suit suit;
    private Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return this.getNumber().equals(Denomination.ACE);
    }

    public Denomination getNumber() {
        return denomination;
    }

    public String getCardInfo() {
        return denomination.getDenomination() + suit.getShapeName();
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
