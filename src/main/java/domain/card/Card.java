package domain.card;

import java.util.Objects;

public class Card {
    private final Denomination denomination;
    private final Suit suit;

    public Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public boolean isAceCard() {
        return this.denomination.equals(Denomination.ACE);
    }

    public int getScore() {
        return denomination.getValue();
    }

    public String getName() {
        return denomination.getName();
    }

    public String getShape() {
        return suit.getName();
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
        return denomination == card.denomination && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, suit);
    }
}
