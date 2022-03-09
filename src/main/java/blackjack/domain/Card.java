package blackjack.domain;

public class Card {

    private final CardNumber cardNumber;
    private final CardShape cardShape;

    public Card(final CardNumber cardNumber, final CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    public CardNumber getCardNumber() {
        return this.cardNumber;
    }

    public String getName() {
        return cardNumber.getName() + cardShape.getShape();
    }
}
