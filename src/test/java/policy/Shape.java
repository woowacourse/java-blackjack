package policy;

import java.util.Arrays;

public enum Shape {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String shape;

    Shape(String shape) {
        this.shape = shape;
    }

    public static Shape check(String shape) {
        return Arrays.stream(Shape.values())
                .filter(shape1 -> shape1.shape.equals(shape))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 카드의 모양은 스페이드, 다이아몬드, 하트, 클로버가 있습니다."));
    }

}
