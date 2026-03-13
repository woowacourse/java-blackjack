package dto;

import model.card.CardNumber;
import model.card.Shape;

public record Card(Shape shape, CardNumber cardNumber) {
    public String getString() {
        return cardNumber().getName() + shape().getName();
    }
}
