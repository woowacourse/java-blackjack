package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;

public class CardFactory {

    private static final Map<String, Card> CACHE = new HashMap<>();

    static {
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                String key = suit.name() + "_" + rank.name();
                CACHE.put(key, resolveCard(suit, rank));
            }
        }
    }

    private CardFactory() {
    }

    public static Card create(CardSuit suit, CardRank rank) {
        String key = suit.name() + "_" + rank.name();
        return CACHE.get(key);
    }

    private static Card resolveCard(CardSuit suit, CardRank rank) {
        if (rank.isAce()) {
            return AceCard.from(suit);
        }
        return NormalCard.of(suit, rank);
    }
}
