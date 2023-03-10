package domain.card;

import java.util.Objects;

public final class Card {

    private final CardShape cardShape;
    private final CardNumber cardNumber;

    private Card(final CardShape cardShape, final CardNumber cardNumber) {
        this.cardShape = cardShape;
        this.cardNumber = cardNumber;
    }

    public static Card of(final CardShape cardShape, final CardNumber cardNumber) {
        return new Card(cardShape, cardNumber);
    }

    public static Card of(final Card card) {
        return new Card(card.cardShape, card.cardNumber);
    }

    public boolean isSameAs(int value) {
        return cardNumber.getValue() == value;
    }

    public boolean isOver(final int value) {
        return cardNumber.getValue() > value;
    }

    public int getCardNumberValue() {
        return cardNumber.getValue();
    }

    public CardShape getShape() {
        return cardShape;
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
        return cardShape == card.cardShape && Objects.equals(cardNumber, card.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardShape, cardNumber);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardShape=" + cardShape +
                ", cardNumber=" + cardNumber +
                '}';
    }
}
