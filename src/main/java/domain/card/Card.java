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

    public int getBaseScore() {
        return cardNumber.getScore();
    }

    @Override
    public String toString() {
        return cardNumber.getNumber() + cardShape.getShape();
    }
}
