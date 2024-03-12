package blackjack.view.object;

import blackjack.domain.card.CardShape;
import java.util.Arrays;

public enum CardShapeOutput {

    HEART_OUTPUT(CardShape.HEART, "하트"),
    CLOVER_OUTPUT(CardShape.CLOVER, "클로버"),
    SPADE_OUTPUT(CardShape.SPADE, "스페이드"),
    DIAMOND_OUTPUT(CardShape.DIAMOND, "다이아몬드");

    private final CardShape cardShape;
    private final String output;

    CardShapeOutput(CardShape cardShape, String output) {
        this.cardShape = cardShape;
        this.output = output;
    }

    public static String convertShapeToOutput(CardShape cardShape) {
        return Arrays.stream(values())
                .filter(cardShapeOutput -> cardShapeOutput.cardShape == cardShape)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드 문양입니다."))
                .output;
    }
}
