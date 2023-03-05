package domain.card;

import java.util.Objects;

public final class Card {

    private final CardShape cardShape;
    private final CardNumber cardNumber;

    private Card(final CardShape cardShape, final CardNumber cardNumber) {
        this.cardShape = cardShape;
        this.cardNumber = cardNumber;
    }

    public static Card create(final CardShape cardShape, final CardNumber cardNumber) {
        return new Card(cardShape, cardNumber);
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

}
