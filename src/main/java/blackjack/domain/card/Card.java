package blackjack.domain.card;

public class Card {

    private final CardShape cardShape;
    private final CardNumber cardNumber;

    public Card(CardShape cardShape, CardNumber cardNumber) {
        this.cardShape = cardShape;
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return cardNumber.getName() + cardShape.getShape();
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }
}
