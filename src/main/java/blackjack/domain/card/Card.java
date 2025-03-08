package blackjack.domain.card;

import java.util.Objects;

public class Card {
    public static final int MAX_ACE_VALUE = 11;
    public static final int MIN_ACE_VALUE = 1;

    private final CardShape shape;
    private final CardType type;

    public Card(final CardShape shape, final CardType type) {
        this.shape = shape;
        this.type = type;
    }

    public int getTypeValue() {
        return type.getValue();
    }

    public boolean isAce() {
        return type == CardType.ACE;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Card card = (Card) object;
        return shape == card.shape && type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, type);
    }

    @Override
    public String toString() {
        return type.getDisplayName() + shape.getDisplayName();
    }
}
