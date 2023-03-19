package blackjack.domain.card;

import java.util.Arrays;

public enum Shape {

    DIAMOND("다이아몬드"),
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버");

    private static final String INVALID_SHAPE_ERROR_MESSAGE = "해당 이름을 가진 모양이 존재하지 않습니다.";

    private final String value;

    Shape(final String value) {
        this.value = value;
    }

    public static Shape from(final String value) {
        return Arrays.stream(Shape.values())
                .filter(shape -> shape.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_SHAPE_ERROR_MESSAGE));
    }

    public String getValue() {
        return value;
    }
}
