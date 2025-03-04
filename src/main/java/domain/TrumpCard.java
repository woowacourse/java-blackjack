package domain;

public class TrumpCard {
    private final CardShape cardShape;
    private final CardNumber cardNumber;

    public TrumpCard(int cardShapeIndex, int cardNumberIndex) {
        this.cardShape = CardShape.pickCardShape(cardShapeIndex);
        this.cardNumber = CardNumber.pick(cardShapeIndex);
    }

    public CardShape getCardShape() {
        return cardShape;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }
}
