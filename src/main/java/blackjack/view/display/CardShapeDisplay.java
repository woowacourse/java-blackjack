package blackjack.view.display;

import blackjack.domain.card.CardShape;

import java.util.Arrays;

public enum CardShapeDisplay {
    HEART(CardShape.HEART, "하트"),
    DIAMOND(CardShape.DIAMOND, "다이아몬드"),
    SPADE(CardShape.SPADE, "스페이드"),
    CLOVER(CardShape.CLOVER, "클로버"),
    ;
    
    private final CardShape cardShape;
    private final String cardShapeDisplay;
    
    CardShapeDisplay(final CardShape cardShape, final String cardShapeDisplay) {
        this.cardShape = cardShape;
        this.cardShapeDisplay = cardShapeDisplay;
    }
    
    public static String parseCardShape(CardShape cardShape) {
        return Arrays.stream(CardShapeDisplay.values())
                .filter(displayCard -> displayCard.cardShape.equals(cardShape))
                .findAny()
                .map(displayCard -> displayCard.cardShapeDisplay)
                .orElseThrow();
    }
}
