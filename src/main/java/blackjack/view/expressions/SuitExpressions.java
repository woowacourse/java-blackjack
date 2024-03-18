package blackjack.view.expressions;

import blackjack.domain.card.Suit;
import java.util.Arrays;

public enum SuitExpressions {
    SPADE(Suit.SPADE, "스페이드"),
    HEART(Suit.HEART, "하트"),
    DIAMOND(Suit.DIAMOND, "다이아몬드"),
    CLOVER(Suit.CLOVER, "클로버"),
    ;

    private final Suit suit;
    private final String value;

    SuitExpressions(final Suit suit, final String value) {
        this.suit = suit;
        this.value = value;
    }

    public static String mapCardShapeToString(final Suit suit) {
        return Arrays.stream(values())
                .filter(suitExpression -> suitExpression.suit.equals(suit))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 카드 모양입니다."))
                .value;
    }

    public String getValue() {
        return value;
    }
}
