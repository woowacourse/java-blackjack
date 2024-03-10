package blackjack.view.mapper;

import blackjack.domain.Shape;
import java.util.Arrays;

public enum ShapeMapper {

    HEART("하트"), DIAMOND("다이아몬드"), CLOVER("클로버"), SPADE("스페이드");

    private final String shapeName;

    ShapeMapper(String shapeName) {
        this.shapeName = shapeName;
    }

    public static ShapeMapper findByShape(Shape shape) {
        return Arrays.stream(values())
                .filter(shapeMapper -> shape.isSameName(shapeMapper.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 모양이 없습니다."));
    }

    public String getShapeName() {
        return shapeName;
    }
}
