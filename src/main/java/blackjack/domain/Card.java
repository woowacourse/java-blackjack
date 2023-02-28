package blackjack.domain;

public class Card {
    private TrumpShape trumpShape;
    private TrumpNumber trumpNumber;

    public Card(TrumpShape trumpShape, TrumpNumber trumpNumber) {
        this.trumpShape = trumpShape;
        this.trumpNumber = trumpNumber;
    }

    public TrumpShape getTrumpShape() {
        return trumpShape;
    }

    public TrumpNumber getTrumpNumber() {
        return trumpNumber;
    }
}
