package domain.card;

import java.util.Objects;

public class Card {

    private final Type type;
    private final Value value;

    public Card(final Type type, final Value value) {
        this.type = type;
        this.value = value;
    }

    public boolean isAce() {
        return value.isAce();
    }

    public int getScore() {
        return value.getScore();
    }

    public Type getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Card otherCard = (Card) other;
        return type == otherCard.type && value == otherCard.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
