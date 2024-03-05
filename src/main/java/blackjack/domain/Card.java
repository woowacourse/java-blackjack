package blackjack.domain;

public class Card {

    private final CardShape cardShape;
    private final CardNumber cardNumber;

    public Card(CardShape cardShape, CardNumber cardNumber) {
        this.cardShape = cardShape;
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber.getValue();
    }
}
