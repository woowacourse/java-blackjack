package blackjack.domain.cards.card;

import blackjack.domain.cards.card.denomination.Denomination;
import blackjack.domain.cards.card.denomination.Suit;
import java.util.Objects;

public final class Card {
    private final Denomination denomination;
    private final Suit suit;

    private Card(final Denomination denomination, final Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public static Card of(final Denomination denomination, final Suit suit) {
        return new Card(denomination, suit);
    }

    public boolean isAce() {
        return this.denomination.equals(Denomination.ACE);
    }

    public int getPoint() {
        return denomination.getPoint();
    }

    public String getDenomination() {
        return denomination.getInitial();
    }

    public String getSuit() {
        return suit.get();
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
        return denomination == card.denomination && suit == card.suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "denomination=" + denomination +
                ", suit=" + suit +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, suit);
    }
}
