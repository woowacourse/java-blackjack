package domain;

import java.util.Objects;

public class TrumpCard {
    private final CardShape cardShape;
    private final CardNumber cardNumber;

    public TrumpCard(CardShape cardShape, CardNumber cardNumber) {
        this.cardShape = cardShape;
        this.cardNumber = cardNumber;
    }

    public CardShape getCardShape() {
        return cardShape;
    }

    public int getCardNumber() {
        return cardNumber.getValue();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        TrumpCard trumpCard = (TrumpCard) object;
        return cardShape == trumpCard.cardShape && cardNumber == trumpCard.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardShape, cardNumber);
    }
}
