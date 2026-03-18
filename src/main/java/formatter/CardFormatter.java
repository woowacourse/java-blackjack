package formatter;

import domain.card.Card;
import domain.enums.Rank;
import domain.enums.Suit;

public final class CardFormatter {

    private CardFormatter() {
    }

    public static String format(Card card) {
        return format(card.rank()) + format(card.suit());
    }

    private static String format(Rank rank) {
        return switch (rank) {
            case TWO -> "2";
            case THREE -> "3";
            case FOUR -> "4";
            case FIVE -> "5";
            case SIX -> "6";
            case SEVEN -> "7";
            case EIGHT -> "8";
            case NINE -> "9";
            case TEN -> "10";
            case JACK -> "J";
            case QUEEN -> "Q";
            case KING -> "K";
            case ACE -> "A";
        };
    }

    private static String format(Suit suit) {
        return switch (suit) {
            case HEART -> "하트";
            case DIAMOND -> "다이아";
            case SPADE -> "스페이드";
            case CLOVER -> "클로버";
        };
    }
}
