package domain.card;

import java.util.Objects;

public class Card {
    private final Denomination denomination;
    private final Suit suit;

    public Card(final Denomination denomination, final Suit symbol) {
        this.denomination = denomination;
        this.suit = symbol;
    }

    public int score() {
        return denomination.getValue();
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public Denomination denomination() {
        return denomination;
    }

    public Suit suit() {
        return suit;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return denomination == card.denomination && Objects.equals(suit, card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, suit);
    }
}
