package domain;

public class TrumpCard {
    private final CardShape cardShape;
    private final CardNumber cardNumber;

    public TrumpCard(CardShape cardShape, CardNumber cardNumber) {
        this.cardShape = cardShape;
        this.cardNumber = cardNumber;
    }

    public CardShape getCardShape() {
        return cardShape;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public int getCardNumberValue() {
        return cardNumber.getValue();
    }

}
