package domain;

import java.util.Arrays;

public enum Shape {
    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private final String shapeKoreanName;

    Shape(String shapeKoreanName) {
        this.shapeKoreanName = shapeKoreanName;
    }

    public String getShape() {
        return shapeKoreanName;
    }
}
