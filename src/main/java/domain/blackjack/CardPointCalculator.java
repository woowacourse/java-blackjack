package domain.blackjack;

import domain.card.Card;
import domain.card.CardName;

class CardPointCalculator {
    private static final int MAX_NUMBER = 10;

    private CardPointCalculator() {
    }

    static CardPoint calculate(Card card) {
        CardName cardName = card.name();
        int cardNumber = cardName.getCardNumber();
        if (cardNumber > MAX_NUMBER) {
            return new CardPoint(MAX_NUMBER);
        }
        return new CardPoint(cardNumber);
    }
}
