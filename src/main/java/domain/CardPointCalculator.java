package domain;

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
