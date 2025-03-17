package card;

public class Card {

    private final CardShape cardShape;
    private final CardNumber cardNumber;

    public Card(CardShape cardShape, CardNumber cardNumber) {
        this.cardShape = cardShape;
        this.cardNumber = cardNumber;
    }

    public boolean isSameCardShape(CardShape cardShape) {
        return this.cardShape.isSame(cardShape);
    }

    public boolean isSameCardNumber(CardNumber cardNumber) {
        return this.cardNumber.isSame(cardNumber);
    }

    public int getCardNumberValue() {
        return cardNumber.getValue();
    }
}
