package domain;

public class Card {
    private CardNumber cardNumber;
    private CardShape cardShape;

    private Card(CardNumber cardNumber, CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    };

    public static Card of(CardNumber cardNumber, CardShape cardShape) {
        return new Card(cardNumber, cardShape);
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public CardShape getCardShape() {
        return cardShape;
    }
}
