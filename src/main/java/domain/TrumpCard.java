package domain;

public class TrumpCard {
    private final CardShape cardShape;
    private final CardNumber cardNumber;

    public TrumpCard(NumberGenerator numberGenerator) {
        this.cardShape = CardShape.pickCardShape(numberGenerator.pickRangeOfNumber(1, 4));
        this.cardNumber = CardNumber.pick(numberGenerator.pickRangeOfNumber(1, 13));
    }

    public CardShape getCardShape() {
        return cardShape;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }
}
