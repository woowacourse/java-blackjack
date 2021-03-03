package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private final Shape shape;
    private final Denomination value;

    public Card(Shape shape, Denomination value) {
        this.shape = shape;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (!Objects.equals(shape, card.shape)) return false;
        return Objects.equals(value, card.value);
    }

    @Override
    public int hashCode() {
        int result = shape != null ? shape.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
