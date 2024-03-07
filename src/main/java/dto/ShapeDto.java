package dto;

import domain.deck.Shape;

public enum ShapeDto {
    HEART("하트"), DIAMOND("다이아몬드"), CLOVER("클로버"), SPADE("스페이드");

    private final String shapeName;

    ShapeDto(String shapeName) {
        this.shapeName = shapeName;
    }

    public static ShapeDto from(Shape shape) {
        return valueOf(shape.name());
    }

    public String getShapeName() {
        return shapeName;
    }
}
