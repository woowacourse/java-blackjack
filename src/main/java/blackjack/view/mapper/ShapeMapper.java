package blackjack.view.mapper;

import blackjack.domain.card.Shape;
import java.util.Arrays;

public enum ShapeMapper {

    HEART(Shape.HEART, "하트"),
    DIAMOND(Shape.DIAMOND, "다이아몬드"),
    CLOVER(Shape.CLOVER, "클로버"),
    SPADE(Shape.SPADE, "스페이드");

    private final Shape shape;
    private final String shapeName;

    ShapeMapper(Shape shape, String shapeName) {
        this.shape = shape;
        this.shapeName = shapeName;
    }

    public static String findByShape(Shape shape) {
        return Arrays.stream(values())
                .filter(shapeMapper -> shapeMapper.shape == shape)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 모양이 없습니다."))
                .shapeName;
    }
}
