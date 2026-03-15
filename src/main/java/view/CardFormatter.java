package view;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.Map;

public class CardFormatter {
    private static final Map<Suit, String> SUIT_TEXT = Map.of(
            Suit.DIAMOND, "다이아몬드",
            Suit.HEART, "하트",
            Suit.SPADE, "스페이드",
            Suit.CLOVER, "클로버"
    );

    private static String rankText(Rank rank) {
        return switch (rank) {
            case ACE -> "A";
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
        };
    }

    public static String format(Card card) {
        return rankText(card.rank()) + SUIT_TEXT.get(card.suit());
    }
}
