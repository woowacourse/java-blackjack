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

    public static Shape from(String shapeKoreanName) {
        return Arrays.stream(values())
                .filter(shape -> shape.shapeKoreanName.equals(shapeKoreanName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "카드 모양은 하트, 스페이드, 다이아몬드, 클로버 중 하나여야 합니다. 입력값 : " + shapeKoreanName));
    }

    public String getShape() {
        return shapeKoreanName;
    }
}
