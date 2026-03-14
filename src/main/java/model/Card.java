package model;

public record Card(Shape shape, CardNumber cardNumber) {

    public String getString() {
        return cardNumber().getName() + shape().getName();
    }
}
