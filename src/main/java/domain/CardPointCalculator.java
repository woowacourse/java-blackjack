package domain;

public class CardPointCalculator {
    public static CardPoint calculate(Card card) {
        CardValue cardValue = card.value();
        if (cardValue.getCardNumber() > 10) {
            return new CardPoint(10);
        }
        return new CardPoint(cardValue.getCardNumber());
    }
}
