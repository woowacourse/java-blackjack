package blackjack.view;

import blackjack.domain.card.CardShape;

public enum CardShapeStrings {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String value;

    CardShapeStrings(final String value) {
        this.value = value;
    }

    public static String mapCardShapeToString(final CardShape cardShape) {
        if (CardShape.SPADE.equals(cardShape)) {
            return SPADE.value;
        }
        if (CardShape.HEART.equals(cardShape)) {
            return HEART.value;
        }
        if (CardShape.DIAMOND.equals(cardShape)) {
            return DIAMOND.value;
        }
        if (CardShape.CLOVER.equals(cardShape)) {
            return CLOVER.value;
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 카드 모양입니다.");
    }

    public String getValue() {
        return value;
    }
}
