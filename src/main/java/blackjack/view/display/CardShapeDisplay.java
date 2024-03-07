package blackjack.view.display;

import blackjack.card.Shape;
import java.util.Arrays;

public enum CardShapeDisplay {

    HEART(Shape.HEART, "하트"),
    SPADE(Shape.SPADE, "스페이드"),
    CLOVER(Shape.CLOVER, "클로버"),
    DIAMOND(Shape.DIAMOND, "다이아몬드");

    private final Shape shape;
    private final String display;

    CardShapeDisplay(Shape shape, String display) {
        this.shape = shape;
        this.display = display;
    }

    public static String fromShape(Shape shape) {
        return Arrays.stream(CardShapeDisplay.values())
                .filter(displayShape -> displayShape.shape == shape)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 모양입니다."))
                .display;
    }
}
