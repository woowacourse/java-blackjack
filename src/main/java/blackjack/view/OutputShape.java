package blackjack.view;

import java.util.Arrays;

public enum OutputShape {
    DIAMOND("다이아몬드", "다이아몬드"),
    HEART("하트", "하트"),
    SPADE("스페이드", "스페이드"),
    CLOVER("클로버", "클로버");

    private final String shape;
    private final String output;

    OutputShape(String shape, String output) {
        this.shape = shape;
        this.output = output;
    }

    public static String match(String shape) {
        return Arrays.stream(values())
                .filter(outputLetter -> outputLetter.isSame(shape))
                .findFirst()
                .get().output;
    }

    private boolean isSame(String shape) {
        return this.shape.equals(shape);
    }
}
