package blackjack.view;

import blackjack.domain.card.CardShape;

public enum CardShapeView {

    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String message;

    CardShapeView(String message) {
        this.message = message;
    }

    public static String getShapeMessage(final CardShape cardShape) {
        return CardShapeView.valueOf(cardShape.name()).message;
    }
}
