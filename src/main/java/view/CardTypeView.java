package view;

import domain.constant.CardType;
import java.util.Arrays;

public enum CardTypeView {
    HEART_VIEW(CardType.HEART, "하트"),
    SPADE_VIEW(CardType.SPADE, "스페이드"),
    DIAMOND_VIEW(CardType.DIAMOND, "다이아몬드"),
    CLOVER_VIEW(CardType.CLOVER, "클로버");

    private final CardType cardType;
    private final String symbol;

    CardTypeView(CardType cardType, String symbol) {
        this.cardType = cardType;
        this.symbol = symbol;
    }

    public static CardTypeView getViewByType(CardType cardType) {
        return Arrays.stream(values())
                .filter(cardTypeView -> cardTypeView.cardType == cardType)
                .findFirst()
                .orElse(null);
    }

    public String getSymbol() {
        return symbol;
    }
}
