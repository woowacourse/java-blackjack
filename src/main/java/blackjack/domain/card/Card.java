package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private final CardShape cardShape;
    private final CardNumber cardNumber;

    public Card(final CardShape cardShape, final CardNumber cardNumber) {
        this.cardShape = cardShape;
        this.cardNumber = cardNumber;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
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
