package util;

import domain.card.Denomination;
import domain.card.PlayingCard;
import domain.card.Suit;
import java.util.EnumMap;
import java.util.Map;

public class NameMapper {
    private static final Map<Suit, String> SUIT_NAME_MAPPER = new EnumMap<>(Suit.class);
    private static final Map<Denomination, String> DENOMINATION_NAME_MAPPER = new EnumMap<>(Denomination.class);

    private NameMapper() {
    }

    static {
        DENOMINATION_NAME_MAPPER.put(Denomination.KING, "K");
        DENOMINATION_NAME_MAPPER.put(Denomination.QUEEN, "Q");
        DENOMINATION_NAME_MAPPER.put(Denomination.JACK, "J");
        DENOMINATION_NAME_MAPPER.put(Denomination.ACE, "A");
        DENOMINATION_NAME_MAPPER.put(Denomination.TWO, "2");
        DENOMINATION_NAME_MAPPER.put(Denomination.THREE, "3");
        DENOMINATION_NAME_MAPPER.put(Denomination.FOUR, "4");
        DENOMINATION_NAME_MAPPER.put(Denomination.FIVE, "5");
        DENOMINATION_NAME_MAPPER.put(Denomination.SIX, "6");
        DENOMINATION_NAME_MAPPER.put(Denomination.SEVEN, "7");
        DENOMINATION_NAME_MAPPER.put(Denomination.EIGHT, "8");
        DENOMINATION_NAME_MAPPER.put(Denomination.NINE, "9");
        DENOMINATION_NAME_MAPPER.put(Denomination.TEN, "10");

        SUIT_NAME_MAPPER.put(Suit.CLUBS, "클로버");
        SUIT_NAME_MAPPER.put(Suit.DIAMONDS, "다이아몬드");
        SUIT_NAME_MAPPER.put(Suit.HEARTS, "하트");
        SUIT_NAME_MAPPER.put(Suit.SPADES, "스페이드");
    }

    public static String getCardName(PlayingCard playingCard) {
        return DENOMINATION_NAME_MAPPER.get(playingCard.getDenomination())
                + SUIT_NAME_MAPPER.get(playingCard.getSuit());
    }
}
