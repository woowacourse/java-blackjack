package blackjack.view;

import blackjack.model.cards.CardShape;
import java.util.Arrays;

public enum CardShapeText {
    SPADE(CardShape.SPADE, "스페이드"),
    DIAMOND(CardShape.DIAMOND, "다이아몬드"),
    HEART(CardShape.HEART, "하트"),
    CLOVER(CardShape.CLOVER, "클로버");

    private final CardShape cardShape;
    private final String text;

    CardShapeText(final CardShape cardShape, final String text) {
        this.cardShape = cardShape;
        this.text = text;
    }

    public static CardShapeText from(final CardShape cardShape) {
        return Arrays.stream(values())
                .filter(text -> text.cardShape.equals(cardShape))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않는 카드 숫자입니다."));
    }

    public String getText() {
        return text;
    }
}
