package view.mapper;

import domain.cards.cardinfo.CardShape;
import java.util.Arrays;

public enum CardShapeMapper {

    HEART(CardShape.HEART, "하트"),
    CLOVER(CardShape.CLOVER, "클로버"),
    DIAMOND(CardShape.DIAMOND, "다이아몬드"),
    SPADE(CardShape.SPADE, "스페이드");

    private final CardShape cardShape;
    private final String expression;

    CardShapeMapper(CardShape cardShape, String expression) {
        this.cardShape = cardShape;
        this.expression = expression;
    }

    public static String toExpression(CardShape cardShape) {
        return Arrays.stream(values())
                .filter(cardShapeMapper -> cardShapeMapper.cardShape == cardShape)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 카드의 모양입니다."))
                .expression;
    }
}
