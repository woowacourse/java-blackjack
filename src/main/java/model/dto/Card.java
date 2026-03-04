package model.dto;

import model.CardNumber;
import model.Shape;

public record Card(Shape shape, CardNumber cardNumber) {
}
