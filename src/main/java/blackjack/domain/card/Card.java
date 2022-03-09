package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Suit suit;
    private final Denomination denomination;
    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public String getName() {
        return denomination.getPoint() + suit.getName();
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

    public int getPoint() {
        return denomination.getPoint();
    }
}
