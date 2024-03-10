package blackjack.view.display.deck;

import blackjack.model.deck.Shape;
import java.util.Arrays;

public enum ShapeDisplay {
    SPADE(Shape.SPADE, "스페이드"),
    DIA(Shape.DIA, "다이아"),
    CLOVER(Shape.CLOVER, "클로버"),
    HEART(Shape.HEART, "하트"),
    ;

    private final Shape shape;
    private final String displayName;

    ShapeDisplay(final Shape shape, final String displayName) {
        this.shape = shape;
        this.displayName = displayName;
    }

    public static String getValue(final Shape shape) {
        return Arrays.stream(ShapeDisplay.values())
                .filter(converter -> converter.shape == shape)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 카드 모양에 매칭되는 출력 문자가 없습니다."))
                .displayName;
    }
}
