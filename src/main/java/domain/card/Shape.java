package domain.card;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Shape {
    SPADE("스페이드"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드"),
    HEART("하트");

    private final String shape;

    Shape(final String shape) {
        this.shape = shape;
    }

    public static Shape of(final String shape) {
        return Arrays.stream(Shape.values())
                .filter(element -> element.shape.equals(shape))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public String getShape() {
        return shape;
    }
}
