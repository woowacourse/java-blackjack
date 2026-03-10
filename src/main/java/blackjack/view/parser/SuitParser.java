package blackjack.view.parser;

import blackjack.model.card.Suit;

public class SuitParser {

    public static String parseToLabel(Suit suit) {
        return switch (suit) {
            case DIAMOND -> "다이아몬드";
            case CLUB -> "클로버";
            case SPADE -> "스페이드";
            case HEART -> "하트";
        };
    }
}
