package blackjack.domain.card;

import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import java.util.Objects;

public class Card {
    private final CardShapeType cardShape;
    private final CardNumberType cardNumber;

    public Card(final CardShapeType cardShape, final CardNumberType cardNumber) {
        this.cardShape = cardShape;
        this.cardNumber = cardNumber;
    }

    public int getValue() {
        return cardNumber.getValue();
    }

    public int getBestValue(int scoreSum) {
        return cardNumber.getValue(cardNumber, scoreSum);
    }

    @Override
    public String toString() {
        return cardNumber.getNumber() + cardShape.getShape();
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
        return cardShape == card.cardShape && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardShape, cardNumber);
    }
}
