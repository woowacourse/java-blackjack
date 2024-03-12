package view;

import domain.card.Shape;
import java.util.Arrays;

public enum ShapeView {

    SPADE(Shape.SPADE, "스페이드"),
    HEART(Shape.HEART, "하트"),
    CLOVER(Shape.CLOVER, "클로버"),
    DIAMOND(Shape.DIAMOND, "다이아몬드");

    private final Shape shape;
    private final String value;

    ShapeView(Shape shape, String value) {
        this.shape = shape;
        this.value = value;
    }

    public static String from(final Shape shape) {
        final ShapeView result = Arrays.stream(ShapeView.values())
                .filter(shapeView -> shapeView.shape.equals(shape))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 모양입니다."));

        return result.value;
    }
}
