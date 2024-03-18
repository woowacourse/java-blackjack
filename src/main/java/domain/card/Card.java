package domain.card;

import java.util.Objects;

public class Card {

    private final CardNumber cardNumber;
    private final Shape shape;

    public Card(int number, Shape shape) {
        this.cardNumber = CardNumber.find(number);
        this.shape = shape;
    }

    public boolean isAce() {
        return cardNumber == CardNumber.ACE;
    }

    public int getCardNumber() {
        return cardNumber.getValue();
    }

    public String asString() {
        return cardNumber.getSign() + shape.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardNumber == card.cardNumber && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, shape);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber=" + cardNumber +
                ", shape=" + shape +
                '}';
    }
}
