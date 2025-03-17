package view;

import card.CardRank;
import card.CardShape;

public class CardConverter {
    private CardConverter() {
    }

    public static String createTrumpCard(CardShape cardShape, CardRank cardNumber) {
        return numberToText(cardNumber) + shapeToText(cardShape);
    }

    private static String shapeToText(CardShape cardShape) {
        if (CardShape.DIA == cardShape) {
            return "다이아몬드";
        }
        if (CardShape.SPADE == cardShape) {
            return "스페이드";
        }
        if (CardShape.CLOVER == cardShape) {
            return "클로버";
        }
        return "하트";
    }

    private static String numberToText(CardRank cardNumber) {
        return cardNumber.getDisplayName();
    }
}
