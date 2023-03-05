package utils;

import domain.card.CardShape;

public final class TranslationUtil {

    private TranslationUtil() {
        throw new AssertionError();
    }

    public static String translate(final int cardNumberValue) {
        if (cardNumberValue == 1) {
            return "A";
        }
        if (cardNumberValue == 11) {
            return "J";
        }
        if (cardNumberValue == 12) {
            return "Q";
        }
        if (cardNumberValue == 13) {
            return "K";
        }
        return String.valueOf(cardNumberValue);
    }

    public static String translate(final CardShape cardShape) {
        if (cardShape == CardShape.HEART) {
            return "하트";
        }
        if (cardShape == CardShape.DIAMOND) {
            return "다이아몬드";
        }
        if (cardShape == CardShape.CLOVER) {
            return "클로버";
        }
        if (cardShape == CardShape.SPADE) {
            return "스페이드";
        }
        throw new AssertionError();
    }
}
