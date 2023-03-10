package view;

import domain.game.Result;
import domain.card.CardShape;

public final class TranslationUtil {

    private TranslationUtil() {
        throw new AssertionError();
    }

    public static String translateFaceNumber(final int cardNumberValue) {
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

    public static String translateShape(final CardShape cardShape) {
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

    public static String translatePoint(final int point) {
        if (point == 0) {
            return "버스트";
        }
        return String.valueOf(point);
    }

    public static String translateResult(final Result result) {
        if (result == Result.WIN) {
            return "승";
        }
        if (result == Result.DRAW) {
            return "무";
        }
        if (result == Result.LOSE) {
            return "패";
        }
        throw new AssertionError();
    }
}
