package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private final CardNumber number;
    private final CardShape shape;

    public Card(final CardNumber number, final CardShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public boolean isAce() {
        return number == CardNumber.ACE;
    }

    public int getRealNumber() {
        return number.getNumber();
    }

    public String getNumberName() {
        return number.name();
    }

    public String getShapeName() {
        return shape.name();
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
        return number == card.number && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, shape);
    }
}
