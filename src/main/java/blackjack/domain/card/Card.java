package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private final Shape shape;
    private final CardValue value;

    public Card(Shape shape, CardValue value) {
        this.shape = shape;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return shape == card.shape &&
                value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, value);
    }
}
