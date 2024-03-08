package blackjack.view.description;

import blackjack.domain.card.Shape;
import java.util.Arrays;

public enum ShapeDescription {
    SPADE(Shape.SPADE, "스페이드"),
    HEART(Shape.HEART, "하트"),
    DIAMOND(Shape.DIAMOND, "다이아몬드"),
    CLOVER(Shape.CLOVER, "클로버");

    private final Shape shape;
    private final String description;

    ShapeDescription(Shape shape, String description) {
        this.shape = shape;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescription(Shape shape) {
        return Arrays.stream(values())
                .filter(shapeDescription -> shapeDescription.shape == shape)
                .findFirst()
                .map(ShapeDescription::getDescription)
                .orElseThrow();
    }
}
