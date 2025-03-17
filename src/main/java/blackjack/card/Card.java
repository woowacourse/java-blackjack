package blackjack.card;

import java.util.Objects;

public class Card {

    private final Shape shape;
    private final Denomination denomination;

    public Card(final Shape shape, final Denomination denomination) {
        this.shape = shape;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public int getNumber() {
        return denomination.getNumber();
    }

    public Shape getShape() {
        return shape;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return shape == card.shape && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, denomination);
    }
}
