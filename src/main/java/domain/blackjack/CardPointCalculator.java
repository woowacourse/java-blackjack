package domain.blackjack;

import domain.blackjack.CardPoint;
import domain.card.Card;
import domain.card.CardName;

public class CardPointCalculator {
    public static CardPoint calculate(Card card) {
        CardName cardName = card.name();
        int cardNumber = cardName.getCardNumber();
        if (cardNumber > 10) {
            return new CardPoint(10);
        }
        return new CardPoint(cardNumber);
    }
}
