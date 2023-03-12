package domain.card;

import java.util.Objects;

public class Card {

    private final CardType type;
    private final CardNumber number;

    public Card(final CardType type, final CardNumber number) {
        this.type = type;
        this.number = number;
    }

    public boolean hasNumberOf(final CardNumber otherNumber) {
        return number.equals(otherNumber);
    }

    public CardType getType() {
        return type;
    }

    public CardNumber getNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return type == card.type && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, number);
    }
}
