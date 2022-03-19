package blackjack.domain.card;

import blackjack.domain.card.pattern.Denomination;
import blackjack.domain.card.pattern.Suit;
import java.util.Objects;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public int denominationPoint() {
        return denomination.getPoint();
    }

    public String getName() {
        return denomination.getName() + suit.getName();
    }
}
