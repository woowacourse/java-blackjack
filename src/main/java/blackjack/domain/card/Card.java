package blackjack.domain.card;

import blackjack.domain.card.property.CardNumber;
import blackjack.domain.card.property.CardProperty;
import blackjack.domain.card.property.CardShape;

public class Card {
    private final CardProperty cardProperty;
    private boolean isOpen = true;

    private Card(CardProperty cardProperty) {
        this.cardProperty = cardProperty;
    }

    public static Card of(CardShape shape, CardNumber number) {
        return new Card(CardProperty.of(shape, number));
    }

    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = false;
    }

    public boolean isA() {
        return cardProperty.isA();
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
