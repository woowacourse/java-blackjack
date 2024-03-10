package view;

import domain.card.Shape;

import java.util.Arrays;

public enum ShapeFormat {

    HEART(Shape.HEART, "하트"),
    SPADE(Shape.SPADE, "스페이드"),
    CLOVER(Shape.CLOVER, "클로버"),
    DIA(Shape.DIA, "다이아몬드");

    private final Shape shape;
    private final String name;

    ShapeFormat(Shape shape, String name) {
        this.shape = shape;
        this.name = name;
    }

    public static ShapeFormat from(Shape shape) {
        return Arrays.stream(values())
                .filter(shapeFormat -> shapeFormat.shape == shape)
                .findFirst()
                .orElseThrow();
    }

    public String getName() {
        return name;
    }
}
