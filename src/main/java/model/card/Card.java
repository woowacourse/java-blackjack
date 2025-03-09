package model.card;

import java.util.Objects;

public class Card {
    private final CardNumber number;
    private final CardShape cardShape;

    public Card(final CardNumber number, final CardShape cardShape) {
        this.number = number;
        this.cardShape = cardShape;
    }

    public boolean isSameNumber(final CardNumber number) {
        return this.number == number;
    }

    public int getNumberValue() {
        return number.getNumber();
    }

    public CardNumber getNumber() {
        return number;
    }

    public CardShape getShape() {
        return cardShape;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return number == card.number && cardShape == card.cardShape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, cardShape);
    }
}
