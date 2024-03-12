package view.notations;

import domain.cards.cardinfo.CardShape;

import java.util.Arrays;

public enum CardShapeNotation {

    CLOVER(CardShape.CLOVER, "클로버"),
    DIAMOND(CardShape.DIAMOND, "다이아몬드"),
    SPADE(CardShape.SPADE, "스페이드"),
    HEART(CardShape.HEART, "하트");

    private final CardShape cardShape;
    private final String notation;

    CardShapeNotation(CardShape cardShape, String notation) {
        this.cardShape = cardShape;
        this.notation = notation;
    }

    public static String findNotationByCardShape(CardShape cardShape) {
        return Arrays.stream(values()).filter(cardShapeNotation -> cardShapeNotation.cardShape.equals(cardShape))
                .findFirst()
                .map(cardShapeNotation -> cardShapeNotation.notation)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 카드 모양이 없습니다."));
    }
}
