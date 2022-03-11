package blackjack.domain.card;

import blackjack.domain.card.property.CardNumber;
import blackjack.domain.card.property.CardProperty;
import blackjack.domain.card.property.CardShape;

public class Card {
    private final CardProperty cardProperty;
    private boolean isOpen = true;

    public Card(CardShape shape, CardNumber number) {
        this.cardProperty = new CardProperty(shape, number);
    }

    public CardShape getCardShape() {
        return cardProperty.getShape();
    }

    public CardNumber getCardNumber() {
        return cardProperty.getNumber();
    }

    public void close() {
        isOpen = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open() {
        isOpen = true;
    }

    public boolean isA() {
        return cardProperty.isA();
    }
}
