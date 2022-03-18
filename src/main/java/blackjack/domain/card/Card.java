package blackjack.domain.card;

import blackjack.domain.card.property.CardNumber;
import blackjack.domain.card.property.CardProperty;
import blackjack.domain.card.property.CardShape;

public class Card {
    private final CardProperty cardProperty;
    private boolean isOpen;

    private Card(CardShape cardShape, CardNumber cardNumber, boolean isOpen) {
        this.cardProperty = new CardProperty(cardShape, cardNumber);
        this.isOpen = isOpen;
    }

    public static Card of(CardShape shape, CardNumber number) {
        return new Card(shape, number, true);
    }

    public static Card of(CardShape shape, CardNumber number, boolean isOpen) {
        return new Card(shape, number, isOpen);
    }

    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = false;
    }

    public boolean isAce() {
        return cardProperty.isAce();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public CardShape getCardShape() {
        return cardProperty.getShape();
    }

    public CardNumber getCardNumber() {
        return cardProperty.getNumber();
    }
}
