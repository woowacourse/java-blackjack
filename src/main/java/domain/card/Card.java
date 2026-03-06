package domain.card;

public class Card {
    private final CardNumber cardNumber;
    private final CardShape cardShape;

    public Card(CardNumber cardNumber, CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public int getScore() {
        if (cardNumber == CardNumber.ACE) {
            return 1;
        }
        if (cardNumber == CardNumber.JACK || cardNumber == CardNumber.QUEEN || cardNumber == CardNumber.KING) {
            return 10;
        }
        return Integer.parseInt(cardNumber.getNumber());
    }

    @Override
    public String toString() {
        return cardNumber.getNumber() + cardShape.getShape();
    }
}
