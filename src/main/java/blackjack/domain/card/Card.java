package blackjack.domain.card;

public class Card {
    private final CardNumber cardNumber;
    private final Shape shape;

    public Card(CardNumber cardNumber, Shape shape) {
        this.cardNumber = cardNumber;
        this.shape = shape;
    }

    public boolean isAce() {
        return CardNumber.ACE.equals(cardNumber);
    }

    public int getScore() {
        return cardNumber.getScore();
    }

    @Override
    public String toString() {
        return cardNumber.getNumber() + shape.getName();
    }
}