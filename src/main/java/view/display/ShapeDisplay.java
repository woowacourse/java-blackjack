package view.display;

import domain.card.Shape;
import domain.card.Value;
import java.util.EnumMap;
import java.util.Map;

public final class ShapeDisplay {
    private final Map<Shape, String> shapeDisplay;

    public ShapeDisplay() {
        this.shapeDisplay = initializeShapeDisplay();
    }

    public String findDisplayOf(Shape shape) {
        return shapeDisplay.get(shape);
    }

    private Map<Shape, String> initializeShapeDisplay() {
        Map<Shape, String> shapeDisplay = new EnumMap<>(Shape.class);
        shapeDisplay.put(Shape.SPADE, "스페이드");
        shapeDisplay.put(Shape.CLOVER, "클로버");
        shapeDisplay.put(Shape.DIAMOND, "클로버");
        shapeDisplay.put(Shape.HEART, "하트");
        return shapeDisplay;
    }
}
