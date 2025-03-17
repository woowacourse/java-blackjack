package blackjack.view.display;

import blackjack.domain.card.CardShape;

import java.util.EnumMap;
import java.util.Map;

public enum CardShapeDisplay {
    HEART(CardShape.HEART, "하트"),
    DIAMOND(CardShape.DIAMOND, "다이아몬드"),
    SPADE(CardShape.SPADE, "스페이드"),
    CLOVER(CardShape.CLOVER, "클로버"),
    ;
    
    private static final Map<CardShape, String> CARD_SHAPES = new EnumMap<>(CardShape.class);
    
    static {
        for (CardShapeDisplay cardShape : CardShapeDisplay.values()) {
            CARD_SHAPES.put(cardShape.cardShape, cardShape.cardShapeDisplay);
        }
    }
    
    private final CardShape cardShape;
    private final String cardShapeDisplay;
    
    CardShapeDisplay(final CardShape cardShape, final String cardShapeDisplay) {
        this.cardShape = cardShape;
        this.cardShapeDisplay = cardShapeDisplay;
    }
    
    public static String parseCardShape(CardShape cardShape) {
        return CARD_SHAPES.get(cardShape);
    }
}
