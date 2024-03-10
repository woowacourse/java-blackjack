package blackjack.view;

import blackjack.model.cards.CardShape;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum CardShapeSymbol {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String symbol;

    CardShapeSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static String convertToSymbol(CardShape cardShape) {
        return Arrays.stream(values())
                .filter(shapeSymbol -> shapeSymbol.name().equals(cardShape.name()))
                .findFirst()
                .map(CardShapeSymbol::getSymbol)
                .orElseThrow(() -> new NoSuchElementException("올바르지 않은 카드 모양입니다."));
    }

    public String getSymbol() {
        return symbol;
    }
}
