package view;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;

import java.util.EnumMap;
import java.util.Map;

public final class CardParser {

    private static final Map<Denomination, String> denominationParser = new EnumMap<>(Denomination.class);
    private static final Map<Suit, String> suitParser = new EnumMap<>(Suit.class);

    static {
        denominationParser.put(Denomination.ACE, "A");
        denominationParser.put(Denomination.TWO, "2");
        denominationParser.put(Denomination.THREE, "3");
        denominationParser.put(Denomination.FOUR, "4");
        denominationParser.put(Denomination.FIVE, "5");
        denominationParser.put(Denomination.SIX, "6");
        denominationParser.put(Denomination.SEVEN, "7");
        denominationParser.put(Denomination.EIGHT, "8");
        denominationParser.put(Denomination.NINE, "9");
        denominationParser.put(Denomination.TEN, "10");
        denominationParser.put(Denomination.JACK, "J");
        denominationParser.put(Denomination.QUEEN, "Q");
        denominationParser.put(Denomination.KING, "K");

        suitParser.put(Suit.SPADE, "스페이드");
        suitParser.put(Suit.HEART, "하트");
        suitParser.put(Suit.CLOVER, "클로버");
        suitParser.put(Suit.DIAMOND, "다이아몬드");
    }

    public static String parse(final Card card) {
        return denominationParser.get(card.getDenomination()) + suitParser.get(card.getSuit());
    }
}
