package blackjack.domain;

public class Card {

    private final TrumpShape trumpShape;
    private final TrumpNumber trumpNumber;

    public Card(TrumpShape trumpShape, TrumpNumber trumpNumber) {
        this.trumpShape = trumpShape;
        this.trumpNumber = trumpNumber;
    }

    public String getCardName() {
        return trumpNumber.getName() + trumpShape.getShape();
    }

    public TrumpShape getTrumpShape() {
        return trumpShape;
    }

    public TrumpNumber getTrumpNumber() {
        return trumpNumber;
    }
}
