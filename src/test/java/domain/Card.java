package domain;

import java.util.Objects;

public class Card {
    private final CardNumber cardNumber;
    private final CardShape cardShape;

    public Card(final CardNumber cardNumber, final CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    public int getCardNumber() {
        return cardNumber.getValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Card card = (Card) o;
        return cardNumber == card.cardNumber && cardShape == card.cardShape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardShape);
    }
}
