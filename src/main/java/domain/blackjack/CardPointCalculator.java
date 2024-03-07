package domain.blackjack;

import static domain.card.CardName.TEN;

import domain.card.Card;
import domain.card.CardName;

class CardPointCalculator {
    static CardPoint calculate(Card card) {
        CardName cardName = card.name();
        int cardNumber = cardName.getCardNumber();
        if (cardNumber > TEN.getCardNumber()) {
            return new CardPoint(TEN.getCardNumber());
        }
        return new CardPoint(cardNumber);
    }
}
