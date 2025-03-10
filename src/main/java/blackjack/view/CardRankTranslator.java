package blackjack.view;


import blackjack.common.ErrorMessage;
import blackjack.domain.CardRank;
import java.util.HashMap;
import java.util.Map;

public final class CardRankTranslator {

    private static final Map<CardRank, String> rankDescriptions = new HashMap<>();

    static {
        rankDescriptions.put(CardRank.TWO, "2");
        rankDescriptions.put(CardRank.THREE, "3");
        rankDescriptions.put(CardRank.FOUR, "4");
        rankDescriptions.put(CardRank.FIVE, "5");
        rankDescriptions.put(CardRank.SIX, "6");
        rankDescriptions.put(CardRank.SEVEN, "7");
        rankDescriptions.put(CardRank.EIGHT, "8");
        rankDescriptions.put(CardRank.NINE, "9");
        rankDescriptions.put(CardRank.TEN, "10");
        rankDescriptions.put(CardRank.JACK, "J");
        rankDescriptions.put(CardRank.QUEEN, "Q");
        rankDescriptions.put(CardRank.KING, "K");
        rankDescriptions.put(CardRank.ACE, "A");
    }

    private CardRankTranslator() {
    }

    public static String getDescription(CardRank rank) {
        if (!rankDescriptions.containsKey(rank)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TRANSLATE.getMessage());
        }

        return rankDescriptions.get(rank);
    }
}

