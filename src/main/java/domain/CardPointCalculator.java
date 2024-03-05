package domain;

public class CardPointCalculator {
    public static CardPoint calculate(Card card) {
        CardName cardName = card.name();
        if (cardName.getCardNumber() > 10) {
            return new CardPoint(10);
        }
        return new CardPoint(cardName.getCardNumber());
    }
}
