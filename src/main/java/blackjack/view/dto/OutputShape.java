package blackjack.view.dto;

import blackjack.domain.card.Shape;

import java.util.Arrays;

public enum OutputShape {
    DIAMOND(Shape.DIAMOND, "다이아몬드"),
    HEART(Shape.HEART, "하트"),
    SPADE(Shape.SPADE, "스페이드"),
    CLOVER(Shape.CLOVER, "클로버");

    private final Shape shape;
    private final String output;

    OutputShape(Shape shape, String output) {
        this.shape = shape;
        this.output = output;
    }

    public static String match(Shape shape) {
        return Arrays.stream(values())
                .filter(outputLetter -> outputLetter.shape.equals(shape))
                .findFirst()
                .get().output;
    }
}
