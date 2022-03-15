package blackjack.domain.card;

import static blackjack.domain.card.Denomination.*;

import java.util.Objects;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public String getSymbol() {
        return denomination.getSymbol();
    }

    public String getSuitName() {
        return suit.getName();
    }

    public int getPoint() {
        return denomination.getPoint();
    }

    public boolean isAce() {
        return denomination == ACE;
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
