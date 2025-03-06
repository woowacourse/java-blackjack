package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Shape shape;
    private final Denomination denomination;

    public Card(final Shape shape, final Denomination denomination) {
        this.shape = shape;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return this.denomination == Denomination.A;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Card card)) {
            return false;
        }
        return shape == card.shape && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, denomination);
    }

    public Shape getShape() {
        return shape;
    }

    public String getDenominationName() {
        return denomination.getName();
    }

    public int getCardMinNumber() {
        return denomination.getMinNumber();
    }

    public int getCardMaxNumber() {
        return denomination.getMaxNumber();
    }
}
