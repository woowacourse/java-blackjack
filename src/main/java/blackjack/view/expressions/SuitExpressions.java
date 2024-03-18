package blackjack.view.expressions;

import blackjack.domain.card.Suit;

public enum SuitExpressions {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String value;

    SuitExpressions(final String value) {
        this.value = value;
    }

    public static String mapCardShapeToString(final Suit suit) {
        if (Suit.SPADE.equals(suit)) {
            return SPADE.value;
        }
        if (Suit.HEART.equals(suit)) {
            return HEART.value;
        }
        if (Suit.DIAMOND.equals(suit)) {
            return DIAMOND.value;
        }
        if (Suit.CLOVER.equals(suit)) {
            return CLOVER.value;
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 카드 모양입니다.");
    }

    public String getValue() {
        return value;
    }
}
