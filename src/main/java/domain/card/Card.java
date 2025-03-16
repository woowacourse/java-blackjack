package domain.card;

import java.util.Objects;

public class Card {
    private final CardNumber number;
    private final CardShape shape;

    public Card(CardNumber number, CardShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public int getNumberValue() {
        return number.value;
    }

    public CardNumber getCardNumber() {
        return number;
    }

    public CardShape getCardShape() {
        return shape;
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
        return Objects.equals(number, card.number) && Objects.equals(shape, card.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, shape);
    }
}
