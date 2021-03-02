package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Denomination denomination;
    private final Shape shape;

    public Card(Denomination denomination, Shape shape) {
        this.denomination = denomination;
        this.shape = shape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return denomination == card.denomination && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, shape);
    }
}
