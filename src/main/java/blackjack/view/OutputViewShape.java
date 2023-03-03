package blackjack.view;

import blackjack.domain.Shape;
import java.util.Arrays;

public enum OutputViewShape {
    HEART("하트", Shape.HEART),
    DIAMOND("다이아몬드", Shape.DIAMOND),
    SPADE("스페이드", Shape.SPADE),
    CLOVER("클로버", Shape.CLOVER);

    public final String printShape;
    
    public final Shape shape;

    OutputViewShape(final String printShape, final Shape shape) {
        this.printShape = printShape;
        this.shape = shape;
    }

    public static OutputViewShape from(final Shape shape) {
        return Arrays.stream(values())
                .filter(it -> it.shape == shape)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 모양입니다"));
    }

    public String getPrintShape() {
        return printShape;
    }
}
