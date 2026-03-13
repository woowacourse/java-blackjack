package model.card;

public record Card(Shape shape, CardNumber cardNumber) {

    public String getString() {
        return cardNumber().getName() + shape().getName();
    }

    public boolean isAce() {
        return cardNumber == CardNumber.ACE;
    }
}
