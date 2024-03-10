package domain.card;

public class Card {

    private final CardNumber cardNumber;
    private final Shape shape;

    public Card(int number, Shape shape) {
        this.cardNumber = CardNumber.find(number);
        this.shape = shape;
    }

    public boolean isAce() {
        return cardNumber.isAce();
    }

    public int getCardNumber() {
        return cardNumber.getValue();
    }

    @Override
    public String toString() {
        return cardNumber.getSign() + shape.getShape();
    }
}
